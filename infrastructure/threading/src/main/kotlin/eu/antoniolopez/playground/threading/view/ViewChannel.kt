package eu.antoniolopez.playground.threading.view

import eu.antoniolopez.playground.threading.stream.PublishStream

abstract class ViewChannel<T> : PublishStream<T>()
