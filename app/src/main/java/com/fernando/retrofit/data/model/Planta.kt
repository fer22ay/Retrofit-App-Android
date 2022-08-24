package com.fernando.retrofit.data.model

import com.google.gson.annotations.SerializedName

data class Planta(
    @SerializedName("ID")
    val codigo: Int = -1,
    @SerializedName("NOMBRE")
    val name: String = "",
    @SerializedName("NO_IDENTIFICADOR")
    val no_identificador: String? = "",
    @SerializedName("FACTOR")
    val factor: String? = "",
    @SerializedName("EMPRESA")
    val empresa: Int = -1,
    @SerializedName("LECTURA_INICIAL")
    val lectura: Int = -1,
    @SerializedName("ENCARGADOS")
    val cargados: List<String> = listOf(),
    @SerializedName("TANQUES")
    val tanques: List<String> = listOf(),
)

data class PlantaList(
    @SerializedName("centroComercial")
    val centroComercial: List<Planta> = listOf(),
    @SerializedName("MensajeRespuesta")
    val messageResponse: String = "",
    @SerializedName("OPERACION")
    val operation: Int = -1,
    @SerializedName("MENSAJE")
    val message: String = ""
)