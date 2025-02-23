/*
 * This file is generated by jOOQ.
 */
package com.ispy.ispy.jooq.tables.records


import com.ispy.ispy.jooq.tables.ElaResults

import org.jooq.Record1
import org.jooq.impl.UpdatableRecordImpl


/**
 * This class is generated by jOOQ.
 */
@Suppress("UNCHECKED_CAST")
open class ElaResultsRecord() : UpdatableRecordImpl<ElaResultsRecord>(ElaResults.ELA_RESULTS) {

    open var id: Int?
        set(value): Unit = set(0, value)
        get(): Int? = get(0) as Int?

    open var imageId: Int?
        set(value): Unit = set(1, value)
        get(): Int? = get(1) as Int?

    open var tamperingLikelihood: Double?
        set(value): Unit = set(2, value)
        get(): Double? = get(2) as Double?

    open var detectedEla: Boolean?
        set(value): Unit = set(3, value)
        get(): Boolean? = get(3) as Boolean?

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    override fun key(): Record1<Int?> = super.key() as Record1<Int?>

    /**
     * Create a detached, initialised ElaResultsRecord
     */
    constructor(id: Int? = null, imageId: Int? = null, tamperingLikelihood: Double? = null, detectedEla: Boolean? = null): this() {
        this.id = id
        this.imageId = imageId
        this.tamperingLikelihood = tamperingLikelihood
        this.detectedEla = detectedEla
        resetChangedOnNotNull()
    }
}
