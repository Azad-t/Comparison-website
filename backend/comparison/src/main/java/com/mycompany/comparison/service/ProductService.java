package com.mycompany.comparison.service;

import com.mycompany.comparison.entity.Product;
import com.mycompany.comparison.repository.ProductRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository; // 注入 Repository 实例

    public List<Product> scrapeProductsByName(String productName) {
        // ChromeDriver 设置
        System.setProperty("webdriver.chrome.driver", "E:\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe"); // 修改为你的路径
        ChromeOptions options = new ChromeOptions();
        // options.addArguments("--headless"); // 启用无头模式
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");

        WebDriver driver = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        List<Product> productList = new ArrayList<>();

        try {
            // 京东爬取
            productList.addAll(scrapeFromJD(driver, wait, productName));
            // 淘宝爬取
            productList.addAll(scrapeFromTaobao(driver, wait, productName));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }

        return productList;
    }

    private List<Product> scrapeFromJD(WebDriver driver, WebDriverWait wait, String productName) {
        List<Product> products = new ArrayList<>();
        try {
            driver.get("https://www.jd.com");
            
            // 查找搜索框并输入商品名称
            WebElement searchInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("key")));
            WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.button[aria-label='搜索']")));
            searchInput.sendKeys(productName);
            searchButton.click();
            
            // 等待页面加载完成
            Thread.sleep(5000); // 可根据需要调整
            TimeUnit.SECONDS.sleep(35); // 等待页面加载，最好用显式等待
    
            System.out.println("开始获取数据");
            
            // 获取页面源代码并解析
            Document doc = Jsoup.parse(driver.getPageSource());
            
            // 根据商品的外部结构提取商品信息
            Elements productElements = doc.select(".gl-i-wrap");
    
            int maxresults = 12;
            int count = 0;
            
            for (var productElement : productElements) {
                if(count > maxresults) break;
                // 获取商品名称
                String name = productElement.select(".p-name a em").text();
                
                // 获取商品价格
                String priceString = productElement.select(".p-price strong i").text();
                double price = 0.0;
                if (!priceString.isEmpty()) {
                    // 价格信息有时会含有非数字字符，确保转换为数字
                    priceString = priceString.replaceAll("[^\\d.]", "");
                    try {
                        price = Double.parseDouble(priceString);
                    } catch (NumberFormatException e) {
                        price = 0.0; // 如果无法解析价格则设置为0
                    }
                }
                
                // 获取商品图片链接
                String imgSrc = productElement.select(".p-img a img").attr("src");
                if (imgSrc.isEmpty()) {
                    imgSrc = productElement.select(".p-img a img").attr("data-lazy-img");
                }
                
                // 获取商品链接
                String productLink = productElement.select(".p-name a").attr("href");
                
                // 创建商品对象并设置属性
                Product product = new Product();
                product.setName(name);
                product.setPrice(price);
                product.setImageUrl(imgSrc);
                product.setPlatform("京东");
                product.setFromUrl(productLink);
    
                // 将商品对象添加到列表中
                products.add(product);
    
                // 保存到数据库
               // productRepository.save(product);
                System.out.println("Saved product: " + name);
                count++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }
    

    private List<Product> scrapeFromTaobao(WebDriver driver, WebDriverWait wait, String productName) {
        List<Product> products = new ArrayList<>();
        try {
            driver.get("https://www.taobao.com");
            WebElement searchInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("q")));
            WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".search-button button")));
            searchInput.sendKeys(productName);
            searchButton.click();
            Thread.sleep(5000); // 等待页面加载
            TimeUnit.SECONDS.sleep(35); // 可根据实际需求调整等待时间
            System.out.println("开始获取淘宝数据");
    
            Document doc = Jsoup.parse(driver.getPageSource());
            Elements productElements = doc.select(".doubleCard--gO3Bz6bu");
            
            int maxresults = 12;
            int count = 0;
            for (var productElement : productElements) {
                if (count > maxresults) {
                    break;
                }
                try {
                    String name = productElement.select(".title--qJ7Xg_90 span").text();
                    String priceInt = productElement.select(".priceInt--yqqZMJ5a").text();
                    String priceFloat = productElement.select(".priceFloat--XpixvyQ1").text();
                    double price = Double.parseDouble((priceInt + priceFloat).isEmpty() ? "0" : (priceInt + priceFloat));
    
                    String imgSrc = productElement.select(".mainPicAdaptWrapper--V_ayd2hD img").attr("data-src");
                    if (imgSrc.isEmpty()) {
                        imgSrc = productElement.select(".mainPicAdaptWrapper--V_ayd2hD img").attr("src");
                    }
    
                    String productLink = productElement.select("a").attr("href");
    
                    Product product = new Product();
                    product.setName(name);
                    product.setPrice(price);
                    product.setImageUrl(imgSrc);
                    product.setPlatform("淘宝");
                    product.setFromUrl(productLink.startsWith("//") ? "https:" + productLink : productLink);
                    products.add(product);
    
                    // 保存到数据库
                  //  productRepository.save(product);
                    System.out.println("Saved product: " + name);
                    count++;
                } catch (Exception e) {
                    System.err.println("Error processing a product element: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }
    

    public List<Product> searchProducts(String keyword) {
        // 使用 Repository 实例查找与关键词匹配的商品
        return productRepository.findByNameContainingIgnoreCase(keyword);
    }
}
