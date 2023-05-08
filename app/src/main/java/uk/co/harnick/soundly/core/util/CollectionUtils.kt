package uk.co.harnick.soundly.core.util

object CollectionUtils {
    fun <T: Any> Collection<T>.containsAny(target: Collection<T>): Boolean {
        return this.intersect(target).isNotEmpty()
    }

    fun <T: Any> MutableCollection<T>.toggle(item: T): MutableCollection<T> {
        val newItem = this
        if (newItem.contains(item)) newItem.remove(item) else newItem.add(item)
        return newItem
    }
}