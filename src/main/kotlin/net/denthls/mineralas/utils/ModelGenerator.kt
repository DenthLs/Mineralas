package net.denthls.mineralas.utils

object ModelGenerator{
    fun createModelJson(id: String, type: String): String {
        return when (type) {
            "sample" -> {
                "{\n" +
                        "  \"parent\": \"mineralas:block/" + type + "\",\n" +
                        "  \"textures\": {\n" +
                        "    \"particle\": \"minecraft:block/stone" + "\",\n" +
                        "    \"ore\": \"mineralas:block/" + id + "\",\n" +
                        "    \"_ore\": \"mineralas:block/" + id + "\",\n" +
                        "    \"stone\": \"minecraft:block/stone" + "\"\n" +
                        "  }\n" +
                        "}"
            }
            "ore" -> {
                "{\n" +
                        "  \"parent\": \"block/" + "cube_all" + "\",\n" +
                        "  \"textures\": {\n" +
                        "    \"all\": \"mineralas:block/" + id + "\"\n" +
                        "  }\n" +
                        "}"
            }
            else -> ""
        }
    }
}

data class ModelJson(
    val sample: Sample = Sample(),
    val ore: Ore = Ore()
) {
    data class Sample(
        val parent: String = "",
        val textures: Textures = Textures()
    ) {
        data class Textures(
            val particle: String = "",
            val ore: String = "",
            val _ore: String = "",
            val stone: String = ""
        )
    }

    data class Ore(
        val parent: String = "",
        val textures: Textures = Textures()
    ) {
        data class Textures(
            val all: String = ""
        )
    }
}