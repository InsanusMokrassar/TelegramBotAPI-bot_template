FROM adoptopenjdk/openjdk11

USER 1000

ENTRYPOINT ["/telegram_bot/bin/telegram_bot", "TOKEN"]

ADD ./build/distributions/telegram_bot.tar /
