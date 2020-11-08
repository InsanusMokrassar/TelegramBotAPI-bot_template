package telegram_bot

import dev.inmo.tgbotapi.extensions.api.bot.getMe
import dev.inmo.tgbotapi.extensions.api.telegramBot

/**
 * This method by default expects one argument in [args] field: telegram bot token
 */
suspend fun main(args: Array<String>) {
    val bot = telegramBot(args.first())

    println(bot.getMe())
}
