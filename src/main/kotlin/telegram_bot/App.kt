package telegram_bot

import dev.inmo.tgbotapi.bot.Ktor.telegramBot
import dev.inmo.tgbotapi.extensions.api.bot.getMe
import dev.inmo.tgbotapi.extensions.api.send.reply
import dev.inmo.tgbotapi.extensions.behaviour_builder.buildBehaviourWithLongPolling
import dev.inmo.tgbotapi.extensions.behaviour_builder.triggers_handling.onCommand
import kotlinx.coroutines.*

/**
 * This method by default expects one argument in [args] field: telegram bot token
 */
suspend fun main(args: Array<String>) {
    // that is your bot
    val bot = telegramBot(args.first())

    // that is kotlin coroutine scope which will be used in requests and parallel works under the hood
    val scope = CoroutineScope(Dispatchers.Default)

    // here should be main logic of your bot
    bot.buildBehaviourWithLongPolling(scope) {
        // in this lambda you will be able to call methods without "bot." prefix
        val me = getMe()

        // this method will create point to react on each /start command
        onCommand("start", requireOnlyCommandInMessage = true) {
            // simply reply :)
            reply(it, "Hello, I am ${me.firstName}")
        }

        // That will be called on the end of bot initiation. After that prinln will be started long polling and bot will
        // react on your commands
        println(me)
    }.join()
}
