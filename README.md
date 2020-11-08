# Telegram Bot Template

That is template for telegram bots based on next stack of technologies:

* [Kotlin Coroutines](https://github.com/Kotlin/kotlinx.coroutines)
* [Kotlin Serialization](https://github.com/Kotlin/kotlinx.serialization)
* [Telegram Bot API Library](https://github.com/InsanusMokrassar/TelegramBotAPI) (by default everything is included like
it was described [here](https://github.com/InsanusMokrassar/TelegramBotAPI#ok-where-should-i-start))

## Default

Since you have used this repo as a template you can simply run command `./gradlew run --args="BOT_TOKEN"` (of course,
replace here `BOT_TOKEN` with your telegram bot token like `1234567890:ABCDEFGHIJKLM_OPqrstuvwxyz012345678`). As an
output you will get your bot information like:

```bash
ExtendedBot(id=ChatId(chatId=1234567890), username=Username(username=@username_of_your_bot), firstName=Name of bot, lastName=, canJoinGroups=(some boolean), canReadAllGroupMessages=(some boolean), supportsInlineQueries=(some boolean))
```

## What next?

There are several ways to continue:

* [Tutorials](https://bookstack.inmo.dev/books/telegrambotapi)
* [Github readme](https://github.com/InsanusMokrassar/TelegramBotAPI)

In other words, this template (and [TelegramBotAPI library](https://github.com/InsanusMokrassar/TelegramBotAPI)) does
not limit you on choosing of way to continue 😊
