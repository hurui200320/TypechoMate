# TypechoMate

Typecho伴侣

## 简介

由于本人不擅长PHP，又加上Typecho上面真的没什么好的评论通知插件。于是我一气之下就是用Java/Kotlin写了这个软件。我之前尝试过写了简单的通知插件，但是整体来说不太满意，网站的速度都感觉被拖慢了，并且代码也非常难看，一点都不优雅。

有一次搬迁网站的时候突发奇想：对于IFTTT和Pushover，他们都是Post到对应的url，那我让Typecho直接把对应的评论数据post到我自己写的软件上不就得了？于是这个软件就是那个idea的具象化。

具体来说，这个软件只接受一个表单POST `/comment`，至于这个表单的格式，可以移步[hurui200320/Comment2Webhook](https://github.com/hurui200320/Comment2Webhook) 。总之就是处理邮件提醒所需要的全部数据都会交给第三方软件，从而免除了第三方软件需要再次查询数据库的烦恼。

这个软件的流程如下：

+ 接受Post请求，从中解析评论数据
+ 发起异步操作
  + 发送邮件
    + 任何情况下都给博客所有者发送新评论通知
	+ 若存在parent comment，则向被回复的评论作者发邮件通知
  + 触发IFTTT，进而使用IFTTT向其他渠道通知
  + 触发Pushover，从而在我的手机上接受通知
+ 返回204

其中发送邮件和触发各种通知的步骤是同时进行的，并且不会阻塞回复204的过程，因此网站那边可以很快地完成插件调用，而不必花时间处理http和smtp这些东西。

## 使用

由于设计的时候没有考虑任何安全措施，因此任何人都可以发送post请求并触发这一系列操作，所以不建议把端口放在公网上。此外为了便于部署，软件在编写的时候就考虑到使用Docker。只要Docker能够开机自启，这个软件就能开机自启。

一些命令行如下：
```bash

docker run -d \
  --name=typecho-mate \
  -p 127.0.0.1:8080:8080 \
  -v /root/TypechoMate:/data \
  --restart unless-stopped \
  ghcr.io/hurui200320/typechomate:master
  
  
docker stop typecho-mate
docker rm typecho-mate
docker image rm ghcr.io/hurui200320/typechomate:master

```
