package edu.uw.ischool.chuns4.quizdroid

interface TopicRepositoryInterface {
    fun getTopics(): List<Topic>
    fun getQuestionsForTopic(target: String?): List<Question>
    fun getAllTopic(): List<String>
    fun getShortDescriptionForTarget(target: String?): String
    fun getLongDescriptionForTarget(target: String?): String
}

class TopicRepository: TopicRepositoryInterface {
    private val topic = listOf(
        Topic(
            title = "Math",
            shortDescription = "Basic math questions",
            longDescription = "A collection of questions covering basic mathematical concepts.",
            questionsList = listOf(
                Question("What is 5 + 7?", listOf("10", "11", "12", "14"), 2),
                Question("What is the square root of 49?", listOf("6", "7", "8", "9"), 1),
                Question("What is 15 divided by 3?", listOf("3", "5", "7", "9"), 1),
                Question("What is 3 * 6?", listOf("9", "12", "15", "18"), 3),
                Question("What is 9 squared?", listOf("72", "81", "90", "99"), 1)
            )
        ),
        Topic(
            title = "Physics",
            shortDescription = "Fundamental physics questions",
            longDescription = "A set of questions that test your knowledge of physics principles and facts.",
            questionsList = listOf(
                Question("What is the unit of force?", listOf("Joule", "Pascal", "Newton", "Watt"), 2),
                Question("What is the acceleration due to gravity on Earth?", listOf("9.8 m/s²", "10 m/s²", "15 m/s²", "5 m/s²"), 0),
                Question("Which subatomic particle has a negative charge?", listOf("Proton", "Neutron", "Electron", "Photon"), 2),
                Question("What does E=mc² represent?", listOf("Newton's law", "Einstein's mass-energy equivalence", "Thermodynamics", "Quantum Mechanics"), 1),
                Question("What is the speed of light?", listOf("150,000 km/s", "300,000 km/s", "500,000 km/s", "1,000,000 km/s"), 1)
            )
        ),
        Topic(
            title = "Marvel Super Heroes",
            shortDescription = "Questions on Marvel superheroes",
            longDescription = "Test your knowledge on Marvel superheroes, their powers, and their universe.",
            questionsList = listOf(
                Question("Who is Tony Stark?", listOf("Captain America", "Iron Man", "Thor", "Hulk"), 1),
                Question("What is the name of Thor's hammer?", listOf("AK-47", "Mjolnir", "Excalibur", "Gungnir"), 1),
                Question("Which metal is used to make Captain America’s shield?", listOf("Iron", "Adamantium", "Vibranium", "Steel"), 2),
                Question("Who is Peter Parker's superhero identity?", listOf("Superman", "Iron Man", "Batman", "Spider-Man"), 3),
                Question("Who is the king of Wakanda?", listOf("T'Challa", "Tony Stark", "Steve Rogers", "Bruce Banner"), 0)
            )
        ),
        Topic(
            title = "History",
            shortDescription = "Historical events and figures",
            longDescription = "A compilation of questions on major events, people, and dates in history.",
            questionsList = listOf(
                Question("Who was the first President of the United States?", listOf("Abraham Lincoln", "George Washington", "Thomas Jefferson", "John Adams"), 1),
                Question("In what year did World War II end?", listOf("1942", "1945", "1950", "1955"), 1),
                Question("Which civilization built the Pyramids?", listOf("Romans", "Greeks", "Egyptians", "Aztecs"), 2),
                Question("Who was known as the 'Maid of Orleans'?", listOf("Cleopatra", "Joan of Arc", "Queen Victoria", "Marie Curie"), 1),
                Question("Who discovered America in 1492?", listOf("Vasco da Gama", "Christopher Columbus", "Marco Polo", "Ferdinand Magellan"), 1)
            )
        ),
        Topic(
            title = "Biology",
            shortDescription = "Basic biology concepts",
            longDescription = "Explore fundamental concepts in biology, including cell biology and plant physiology.",
            questionsList =  listOf(
                Question("What is the powerhouse of the cell?", listOf("Nucleus", "Mitochondria", "Ribosome", "Cell wall"), 1),
                Question("Which gas do plants absorb from the atmosphere?", listOf("Oxygen", "Carbon Dioxide", "Nitrogen", "Helium"), 1),
                Question("What pigment gives plants their green color?", listOf("Hemoglobin", "Chlorophyll", "Carotene", "Melanin"), 1),
                Question("Which organ pumps blood through the body?", listOf("Liver", "Lungs", "Heart", "Kidney"), 2),
                Question("What is the basic unit of life?", listOf("Tissue", "Organ", "Organ System", "Cell"), 3)
            )
        )
    )

    override fun getTopics(): List<Topic> {
        return topic
    }

    override fun getAllTopic(): List<String> {
        return topic.map { it.title }
    }

    override fun getShortDescriptionForTarget(target: String?): String {
        return topic.find { it.title == target }?.shortDescription ?: throw IllegalArgumentException("Topic does not exist.")
    }

    override fun getLongDescriptionForTarget(target: String?): String {
        return topic.find { it.title == target }?.longDescription ?: throw IllegalArgumentException("Topic does not exist.")
    }

    override fun getQuestionsForTopic(target: String?): List<Question> {
        return topic.find { it.title == target }?.questionsList ?: throw IllegalArgumentException("Topic does not exist.")
    }

}