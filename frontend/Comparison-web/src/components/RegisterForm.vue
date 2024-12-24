  <template>
    <form @submit.prevent="handleRegister">
      <div class="form-group">
        <div>
        <label for="username">用户名</label>
        <input v-model="form.username" type="text" id="username" required />
      </div>
      <div>
        <label for="email">邮箱</label>
        <input v-model="form.email" type="email" id="email" required />
      </div>
      <div>
        <label for="password">密码</label>
        <input v-model="form.password" type="password" id="password" required minlength="6" />
      </div>
      <button type="submit">注册</button>
      </div>
      
    </form>
  </template>
  
  <script>
import api from '@/api/api';

  export default {
    data() {
      return {
        form: {
          username: '',
          email: '',
          password: ''
        }
      };
    },
    methods: {
      handleRegister() {
      
        
          api.post('/api/users/register', {
            username: this.form.username,
            password: this.form.password,
            email: this.form.email,
          }).then(response => {
            console.log("response: ",response.data);
            if (response.data) {
              this.$message.success('注册成功！');
              this.$router.push('/login');
            } else {
              console.error(response.data);
              this.$message.error('注册失败');
            }
          }).catch(error => {
            console.error(error);
            this.$message.error('注册失败，请检查表单内容');
          });
        
      
    },
    }
  };
  </script>
  
  <style scoped>
  form {
    display: flex;
    flex-direction: column;
    gap: 15px;
    margin-top: 50px;
    width: 300px; /* 调整宽度 */
  }
  
  .form-group {
    background-color: #64c9e7; /* 紫色背景 */
    display: flex;
    flex-direction: column;
    align-items: center;
    width: 100%; /* 占满容器宽度 */
    padding: 30px; /* 增加内边距 */
    border-radius: 20px; /* 圆角 */
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* 添加阴影 */
  }
  
  label {
    font-size: 16px; /* 字体大小 */
    color: #333; /* 字体颜色 */
  }
  
  input {
    width: 100%; /* 输入框占满容器宽度 */
    padding: 10px; /* 内边距 */
    border: 1px solid #ccc; /* 边框 */
    border-radius: 10px; /* 圆角 */
    margin-bottom: 10px; /* 下边距 */
  }
  
  button {
    width: 100%; /* 按钮占满容器宽度 */
    padding: 10px; /* 内边距 */
    background-color: #84e0e3; /* 按钮背景色 */
    border: none; /* 无边框 */
    border-radius: 10px; /* 圆角 */
    color: white; /* 字体颜色 */
    font-size: 16px; /* 字体大小 */
    cursor: pointer; /* 鼠标悬停时的指针样式 */
    transition: background-color 0.3s; /* 过渡效果 */
  }
  
  button:hover {
    background-color: #70a9ca; /* 悬停时的背景色 */
  }
  </style>
  