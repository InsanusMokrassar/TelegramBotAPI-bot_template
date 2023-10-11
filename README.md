# Telegram Bot Template

That is template for telegram bots based on next stack of technologies:

* [Kotlin Coroutines](https://github.com/Kotlin/kotlinx.coroutines)
* [Kotlin Serialization](https://github.com/Kotlin/kotlinx.serialization)
* [Telegram Bot API Library](https://github.com/InsanusMokrassar/TelegramBotAPI) (by default everything is included like
it was described [here](https://github.com/InsanusMokrassar/TelegramBotAPI#ok-where-should-i-start))

## Default

Since you have used this repo as a template you can copy file `example.config.json` as `local.config.json`, put there your bot token and simply run command `./gradlew run --args="local.config.json"`. As an output you will get your bot information like:

```bash
ExtendedBot(id=ChatId(chatId=1234567890), username=Username(username=@username_of_your_bot), firstName=Name of bot, lastName=, canJoinGroups=(some boolean), canReadAllGroupMessages=(some boolean), supportsInlineQueries=(some boolean))
```

### Docker

In this template there is template-like [docker-compose](docker-compose.yml) and [docker](Dockerfile) files. Besides,
there is [Makefile](Makefile) and you may use something like `make buildAndStartCompose` to start your bot.

It is important to replace `"TOKEN"` in [Dockerfile](Dockerfile) or remove and add some config as a volume.

### Config

But you may set up several things for your bot. You should start with adding client object:

```json
{
  "token": "your bot token",
  "client": {
    "connectionTimeoutMillis": 10000,
    "requestTimeoutMillis": 10000,
    "responseTimeoutMillis": 10000,
    "proxy": {
      "hostname": "127.0.0.1",
      "port": 1080,
      "type": "socks",
      "username": "username",
      "password": "password"
    }
  }
}
```

__Required__ fields:

* `token`
* `client/proxy/hostname` (if you pass `client` and `proxy` fields) - hostname of proxy server
* `client/proxy/password` - password for authentication on proxy server, required if `client/proxy/type` is `socks` and `client/proxy/username` passed

__Optional__ fields:

* `client` - object with client configs
* `client/connectionTimeoutMillis` - timeout for connection to the server
* `client/requestTimeoutMillis` - timeout for request complete (when request taken on server)
* `client/responseTimeoutMillis` - timeout for getting a response after request taken on server
* `client/proxy` - proxy settings
* `client/proxy/port` - port of proxy server
* `client/proxy/type` - type of proxy server (can be `socks` or `http`)
* `client/proxy/username` - username for authentication on proxy server
* `client/proxy/password` - password for authentication on proxy server

Basically, your config looks like an object with token:

```json
{
  "token": "your bot token"
}
```

## What next?

There are several ways to continue:

* [Tutorials](https://docs.inmo.dev/tgbotapi/index.html)
* [Github readme](https://github.com/InsanusMokrassar/TelegramBotAPI)

In other words, this template (and [TelegramBotAPI library](https://github.com/InsanusMokrassar/TelegramBotAPI)) does
not limit you on choosing of way to continue 😊
