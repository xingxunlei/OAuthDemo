# OAuth-Demo

这是一个关于 `OAuth` 授权的 demo。

Demo 中主要采用`授权码`的方式请求 GitHub 的API，获取 access_token 以及 请求 user_info

在使用本demo前，你需要在 GitHub 创建一个你自己的 OAuthApplication

[New OAuth App](https://github.com/settings/applications/new)

并设置 CallBack 地址为 http://localhost:8080/redirect

还要记住 你的 `Client ID` 以及 `Client Secret` 并将其配置在 `application.properties` 文件中

最后，启动本Demo，访问 http://localhost:8080 即可
