/*
 * This file is generated by jOOQ.
 */
package com.ispy.ispy.jooq.com.ispy.spy.jooq.tables


import com.ispy.ispy.jooq.com.ispy.spy.jooq.Public
import com.ispy.ispy.jooq.com.ispy.spy.jooq.keys.NOISE_ANALYSIS_RESULTS_PKEY
import com.ispy.ispy.jooq.com.ispy.spy.jooq.keys.NOISE_ANALYSIS_RESULTS__FK_IMAGE
import com.ispy.ispy.jooq.com.ispy.spy.jooq.tables.Images.ImagesPath
import com.ispy.ispy.jooq.com.ispy.spy.jooq.tables.records.NoiseAnalysisResultsRecord

import kotlin.collections.Collection
import kotlin.collections.List

import org.jooq.Condition
import org.jooq.Field
import org.jooq.ForeignKey
import org.jooq.Identity
import org.jooq.InverseForeignKey
import org.jooq.Name
import org.jooq.Path
import org.jooq.PlainSQL
import org.jooq.QueryPart
import org.jooq.Record
import org.jooq.SQL
import org.jooq.Schema
import org.jooq.Select
import org.jooq.Stringly
import org.jooq.Table
import org.jooq.TableField
import org.jooq.TableOptions
import org.jooq.UniqueKey
import org.jooq.impl.DSL
import org.jooq.impl.Internal
import org.jooq.impl.SQLDataType
import org.jooq.impl.TableImpl


/**
 * This class is generated by jOOQ.
 */
@Suppress("UNCHECKED_CAST")
open class NoiseAnalysisResults(
    alias: Name,
    path: Table<out Record>?,
    childPath: ForeignKey<out Record, NoiseAnalysisResultsRecord>?,
    parentPath: InverseForeignKey<out Record, NoiseAnalysisResultsRecord>?,
    aliased: Table<NoiseAnalysisResultsRecord>?,
    parameters: Array<Field<*>?>?,
    where: Condition?
): TableImpl<NoiseAnalysisResultsRecord>(
    alias,
    Public.PUBLIC,
    path,
    childPath,
    parentPath,
    aliased,
    parameters,
    DSL.comment(""),
    TableOptions.table(),
    where,
) {
    companion object {

        /**
         * The reference instance of <code>public.noise_analysis_results</code>
         */
        val NOISE_ANALYSIS_RESULTS: NoiseAnalysisResults = NoiseAnalysisResults()
    }

    /**
     * The class holding records for this type
     */
    override fun getRecordType(): Class<NoiseAnalysisResultsRecord> = NoiseAnalysisResultsRecord::class.java

    /**
     * The column <code>public.noise_analysis_results.id</code>.
     */
    val ID: TableField<NoiseAnalysisResultsRecord, Int?> = createField(DSL.name("id"), SQLDataType.INTEGER.nullable(false).identity(true), this, "")

    /**
     * The column <code>public.noise_analysis_results.image_id</code>.
     */
    val IMAGE_ID: TableField<NoiseAnalysisResultsRecord, Int?> = createField(DSL.name("image_id"), SQLDataType.INTEGER.nullable(false), this, "")

    /**
     * The column
     * <code>public.noise_analysis_results.tampering_likelihood</code>.
     */
    val TAMPERING_LIKELIHOOD: TableField<NoiseAnalysisResultsRecord, Double?> = createField(DSL.name("tampering_likelihood"), SQLDataType.DOUBLE, this, "")

    /**
     * The column <code>public.noise_analysis_results.detected_noise</code>.
     */
    val DETECTED_NOISE: TableField<NoiseAnalysisResultsRecord, Boolean?> = createField(DSL.name("detected_noise"), SQLDataType.BOOLEAN, this, "")

    private constructor(alias: Name, aliased: Table<NoiseAnalysisResultsRecord>?): this(alias, null, null, null, aliased, null, null)
    private constructor(alias: Name, aliased: Table<NoiseAnalysisResultsRecord>?, parameters: Array<Field<*>?>?): this(alias, null, null, null, aliased, parameters, null)
    private constructor(alias: Name, aliased: Table<NoiseAnalysisResultsRecord>?, where: Condition?): this(alias, null, null, null, aliased, null, where)

    /**
     * Create an aliased <code>public.noise_analysis_results</code> table
     * reference
     */
    constructor(alias: String): this(DSL.name(alias))

    /**
     * Create an aliased <code>public.noise_analysis_results</code> table
     * reference
     */
    constructor(alias: Name): this(alias, null)

    /**
     * Create a <code>public.noise_analysis_results</code> table reference
     */
    constructor(): this(DSL.name("noise_analysis_results"), null)

    constructor(path: Table<out Record>, childPath: ForeignKey<out Record, NoiseAnalysisResultsRecord>?, parentPath: InverseForeignKey<out Record, NoiseAnalysisResultsRecord>?): this(Internal.createPathAlias(path, childPath, parentPath), path, childPath, parentPath, NOISE_ANALYSIS_RESULTS, null, null)

    /**
     * A subtype implementing {@link Path} for simplified path-based joins.
     */
    open class NoiseAnalysisResultsPath : NoiseAnalysisResults, Path<NoiseAnalysisResultsRecord> {
        constructor(path: Table<out Record>, childPath: ForeignKey<out Record, NoiseAnalysisResultsRecord>?, parentPath: InverseForeignKey<out Record, NoiseAnalysisResultsRecord>?): super(path, childPath, parentPath)
        private constructor(alias: Name, aliased: Table<NoiseAnalysisResultsRecord>): super(alias, aliased)
        override fun `as`(alias: String): NoiseAnalysisResultsPath = NoiseAnalysisResultsPath(DSL.name(alias), this)
        override fun `as`(alias: Name): NoiseAnalysisResultsPath = NoiseAnalysisResultsPath(alias, this)
        override fun `as`(alias: Table<*>): NoiseAnalysisResultsPath = NoiseAnalysisResultsPath(alias.qualifiedName, this)
    }
    override fun getSchema(): Schema? = if (aliased()) null else Public.PUBLIC
    override fun getIdentity(): Identity<NoiseAnalysisResultsRecord, Int?> = super.getIdentity() as Identity<NoiseAnalysisResultsRecord, Int?>
    override fun getPrimaryKey(): UniqueKey<NoiseAnalysisResultsRecord> = NOISE_ANALYSIS_RESULTS_PKEY
    override fun getReferences(): List<ForeignKey<NoiseAnalysisResultsRecord, *>> = listOf(
        NOISE_ANALYSIS_RESULTS__FK_IMAGE
    )

    private lateinit var _images: ImagesPath

    /**
     * Get the implicit join path to the <code>public.images</code> table.
     */
    fun images(): ImagesPath {
        if (!this::_images.isInitialized)
            _images = ImagesPath(this, NOISE_ANALYSIS_RESULTS__FK_IMAGE, null)

        return _images;
    }

    val images: ImagesPath
        get(): ImagesPath = images()
    override fun `as`(alias: String): NoiseAnalysisResults = NoiseAnalysisResults(DSL.name(alias), this)
    override fun `as`(alias: Name): NoiseAnalysisResults = NoiseAnalysisResults(alias, this)
    override fun `as`(alias: Table<*>): NoiseAnalysisResults = NoiseAnalysisResults(alias.qualifiedName, this)

    /**
     * Rename this table
     */
    override fun rename(name: String): NoiseAnalysisResults = NoiseAnalysisResults(DSL.name(name), null)

    /**
     * Rename this table
     */
    override fun rename(name: Name): NoiseAnalysisResults = NoiseAnalysisResults(name, null)

    /**
     * Rename this table
     */
    override fun rename(name: Table<*>): NoiseAnalysisResults = NoiseAnalysisResults(name.qualifiedName, null)

    /**
     * Create an inline derived table from this table
     */
    override fun where(condition: Condition?): NoiseAnalysisResults = NoiseAnalysisResults(qualifiedName, if (aliased()) this else null, condition)

    /**
     * Create an inline derived table from this table
     */
    override fun where(conditions: Collection<Condition>): NoiseAnalysisResults = where(DSL.and(conditions))

    /**
     * Create an inline derived table from this table
     */
    override fun where(vararg conditions: Condition?): NoiseAnalysisResults = where(DSL.and(*conditions))

    /**
     * Create an inline derived table from this table
     */
    override fun where(condition: Field<Boolean?>?): NoiseAnalysisResults = where(DSL.condition(condition))

    /**
     * Create an inline derived table from this table
     */
    @PlainSQL override fun where(condition: SQL): NoiseAnalysisResults = where(DSL.condition(condition))

    /**
     * Create an inline derived table from this table
     */
    @PlainSQL override fun where(@Stringly.SQL condition: String): NoiseAnalysisResults = where(DSL.condition(condition))

    /**
     * Create an inline derived table from this table
     */
    @PlainSQL override fun where(@Stringly.SQL condition: String, vararg binds: Any?): NoiseAnalysisResults = where(DSL.condition(condition, *binds))

    /**
     * Create an inline derived table from this table
     */
    @PlainSQL override fun where(@Stringly.SQL condition: String, vararg parts: QueryPart): NoiseAnalysisResults = where(DSL.condition(condition, *parts))

    /**
     * Create an inline derived table from this table
     */
    override fun whereExists(select: Select<*>): NoiseAnalysisResults = where(DSL.exists(select))

    /**
     * Create an inline derived table from this table
     */
    override fun whereNotExists(select: Select<*>): NoiseAnalysisResults = where(DSL.notExists(select))
}
