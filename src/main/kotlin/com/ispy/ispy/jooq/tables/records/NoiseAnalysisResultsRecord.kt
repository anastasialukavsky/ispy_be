/*
 * This file is generated by jOOQ.
 */
package com.ispy.ispy.jooq.tables.records


import com.ispy.ispy.jooq.tables.NoiseAnalysisResults

import org.jooq.Record1
import org.jooq.impl.UpdatableRecordImpl


/**
 * This class is generated by jOOQ.
 */
@Suppress("UNCHECKED_CAST")
open class NoiseAnalysisResultsRecord() : UpdatableRecordImpl<NoiseAnalysisResultsRecord>(NoiseAnalysisResults.NOISE_ANALYSIS_RESULTS) {

    open var id: Int?
        set(value): Unit = set(0, value)
        get(): Int? = get(0) as Int?

    open var imageId: Int?
        set(value): Unit = set(1, value)
        get(): Int? = get(1) as Int?

    open var tamperingLikelihood: Double?
        set(value): Unit = set(2, value)
        get(): Double? = get(2) as Double?

    open var detectedNoise: Boolean?
        set(value): Unit = set(3, value)
        get(): Boolean? = get(3) as Boolean?

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    override fun key(): Record1<Int?> = super.key() as Record1<Int?>

    /**
     * Create a detached, initialised NoiseAnalysisResultsRecord
     */
    constructor(id: Int? = null, imageId: Int? = null, tamperingLikelihood: Double? = null, detectedNoise: Boolean? = null): this() {
        this.id = id
        this.imageId = imageId
        this.tamperingLikelihood = tamperingLikelihood
        this.detectedNoise = detectedNoise
        resetChangedOnNotNull()
    }
}
