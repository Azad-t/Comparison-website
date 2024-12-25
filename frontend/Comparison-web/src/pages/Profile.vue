<template>
    <div class="profile">
      <h1>个人信息</h1>
      <p><strong>用户名：</strong>{{ userInfo.username }}</p>
      <p><strong>邮箱：</strong>{{ userInfo.email }}</p>
      <el-container>
        <el-button type="primary" @click="$router.push('/dashboard')">返回</el-button>
        <el-button type="danger" @click="handleLogout">登出</el-button>
      </el-container>
    </div>
</template>

<script>
export default {
    data() {
        return {
            userInfo: {
                id: '',
                username: '',
                password: '',
                email: '',
            }
        };
    },
    created() {
        const token = localStorage.getItem('userprofile');
        console.log('token: ', token);
        if (token) {
            try {
                // 尝试将获取到的数据解析为JSON格式，如果成功说明格式正确，可以使用
                const parsedToken = JSON.parse(token);
                if (parsedToken.id) {
                    this.userInfo = parsedToken;
                } else {
                    // 如果解析后的数据不符合预期（比如没有id属性），进行相应处理
                    this.$router.push('/login');
                    this.$message.error('用户信息格式不正确，请先登录');
                }
            } catch (error) {
                // 如果解析JSON数据失败，说明数据格式有问题，进行相应处理
                console.error('解析用户信息数据失败：', error);
                this.$router.push('/login');
                this.$message.error('请先登录，用户信息读取错误');
            }
        } else {
            this.$router.push('/login');
            this.$message.error('请先登录');
        }
    },
    methods: {
        handleLogout() {
            localStorage.removeItem('authToken');
            localStorage.removeItem('userprofile');
            this.$router.push('/login');
            this.$message.success('已登出');
        },
    },
};
</script>