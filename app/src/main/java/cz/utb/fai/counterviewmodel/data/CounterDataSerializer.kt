package cz.utb.fai.counterviewmodel.data

import androidx.datastore.core.Serializer
import cz.utb.fai.counterviewmodel.CounterData
import java.io.InputStream
import java.io.OutputStream

object CounterDataSerializer : Serializer<CounterData> {
    override val defaultValue: CounterData
        get() = CounterData.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): CounterData {
        return CounterData.parseFrom(input)
    }

    override suspend fun writeTo(t: CounterData, output: OutputStream) {
        t.writeTo(output)
    }
}