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