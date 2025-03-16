package com.example.core.datastore

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.example.core.datastore_proto.Location
import java.io.InputStream
import com.google.protobuf.InvalidProtocolBufferException
import java.io.OutputStream
import javax.inject.Inject

class LocationSerializer @Inject constructor(): Serializer<Location> {
    override val defaultValue: Location = Location.getDefaultInstance()
    override suspend fun readFrom(input: InputStream): Location {
        try {
            return Location.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(t: Location, output: OutputStream) = t.writeTo(output)
}