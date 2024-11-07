package edu.uw.ischool.chuns4.quizdroid

import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Test

class TopicRepositoryTest {
    private lateinit var repository: TopicRepository

    @Before
    fun setUp() {
        repository = TopicRepository()
    }

    @Test
    fun `getTopics should return a list of topics`() {
        val topics = repository.getTopics()
        assertEquals(5, topics.size)
        assertTrue(topics.any { it.title == "Math" })
        assertTrue(topics.any { it.title == "Physics" })
    }

    @Test
    fun `getAllTopic should return list of topic titles`() {
        val topicTitles = repository.getAllTopic()
        assertEquals(listOf("Math", "Physics", "Marvel Super Heroes", "History", "Biology"), topicTitles)
    }


}