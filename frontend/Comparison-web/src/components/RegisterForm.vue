  <template>
    <form @submit.prevent="handleRegister">
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
    </form>
  </template>
  
  <script>
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
      async handleRegister() {
        try {
          // 调用 API 进行注册
          await this.$store.dispatch('registerUser', this.form);
          this.$router.push('/login');
        } catch (error) {
          alert('注册失败，请重试');
        }
      }
    }
  };
  </script>
  
  <style scoped>
  form {
    display: flex;
    flex-direction: column;
    gap: 15px;
  }
  </style>
  