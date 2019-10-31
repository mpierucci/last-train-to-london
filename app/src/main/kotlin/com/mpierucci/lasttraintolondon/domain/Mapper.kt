package com.mpierucci.lasttraintolondon.domain

// TODO extract to core module when writing features
interface Mapper<in FROM, out TO> {
    fun map(from: FROM): TO
}