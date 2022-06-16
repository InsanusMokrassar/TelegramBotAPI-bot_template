FROM adoptopenjdk/openjdk11

USER 1000

ENTRYPOINT ["/telegram_bot/bin/telegram_bot", "/telegram_bot/local.config.json"]

ADD ./build/distributions/telegram_bot.tar /
ADD ./local.config.json /telegram_bot/
