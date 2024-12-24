package com.mycompany.comparison.service;

import com.mycompany.comparison.entity.Product;
import com.mycompany.comparison.repository.ProductRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository; // 注入 Repository 实例

    public Product fetchPriceFromPlatform(String url) throws IOException {
        if (url.contains("taobao.com")) {
            return fetchPriceFromTaobao(url);
        } else if (url.contains("jd.com")) {
            return fetchPriceFromJD(url);
        } else {
            throw new IllegalArgumentException("Unsupported platform: " + url);
        }
    }

    private Product fetchPriceFromTaobao(String url) throws IOException {
        Document doc = Jsoup.connect(url)
                .userAgent(getRandomUserAgent())
                .get();

        String name = doc.selectFirst(".tb-main-title").text(); // 商品标题
        String price = doc.selectFirst(".tb-rmb-num").text();   // 价格

        Product product = new Product();
        product.setName(name);
        product.setPrice(Double.parseDouble(price));
        product.setLastUpdated(new java.util.Date());
        product.setImageUrl("https://via.placeholder.com/200"); // 示例图片链接

        return product;
    }

    private Product fetchPriceFromJD(String url) throws IOException {
        Document doc = Jsoup.connect(url)
                .userAgent(getRandomUserAgent())
                .get();

        String name = doc.selectFirst(".sku-name").text(); // 商品标题
        String price = doc.selectFirst(".price").text();   // 价格

        Product product = new Product();
        product.setName(name.trim());
        product.setPrice(Double.parseDouble(price.trim()));
        product.setLastUpdated(new java.util.Date());
        product.setImageUrl("https://via.placeholder.com/200"); // 示例图片链接

        return product;
    }

    private String getRandomUserAgent() {
        String[] userAgents = {
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36",
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36",
            "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36"
        };
        return userAgents[new java.util.Random().nextInt(userAgents.length)];
    }

    public List<Product> searchProducts(String keyword) {
        // 使用 Repository 实例查找与关键词匹配的商品
        return productRepository.findByNameContainingIgnoreCase(keyword);
    }
}
