/*
 * This file is generated by jOOQ.
 */
package com.ispy.ispy.jooq.com.ispy.spy.jooq.tables


import com.ispy.ispy.jooq.com.ispy.spy.jooq.Public
import com.ispy.ispy.jooq.com.ispy.spy.jooq.keys.ELA_RESULTS_PKEY
import com.ispy.ispy.jooq.com.ispy.spy.jooq.keys.ELA_RESULTS__FK_IMAGE
import com.ispy.ispy.jooq.com.ispy.spy.jooq.tables.Images.ImagesPath
import com.ispy.ispy.jooq.com.ispy.spy.jooq.tables.records.ElaResultsRecord

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
open class ElaResults(
    alias: Name,
    path: Table<out Record>?,
    childPath: ForeignKey<out Record, ElaResultsRecord>?,
    parentPath: InverseForeignKey<out Record, ElaResultsRecord>?,
    aliased: Table<ElaResultsRecord>?,
    parameters: Array<Field<*>?>?,
    where: Condition?
): TableImpl<ElaResultsRecord>(
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
         * The reference instance of <code>public.ela_results</code>
         */
        val ELA_RESULTS: ElaResults = ElaResults()
    }

    /**
     * The class holding records for this type
     */
    override fun getRecordType(): Class<ElaResultsRecord> = ElaResultsRecord::class.java

    /**
     * The column <code>public.ela_results.id</code>.
     */
    val ID: TableField<ElaResultsRecord, Int?> = createField(DSL.name("id"), SQLDataType.INTEGER.nullable(false).identity(true), this, "")

    /**
     * The column <code>public.ela_results.image_id</code>.
     */
    val IMAGE_ID: TableField<ElaResultsRecord, Int?> = createField(DSL.name("image_id"), SQLDataType.INTEGER.nullable(false), this, "")

    /**
     * The column <code>public.ela_results.tampering_likelihood</code>.
     */
    val TAMPERING_LIKELIHOOD: TableField<ElaResultsRecord, Double?> = createField(DSL.name("tampering_likelihood"), SQLDataType.DOUBLE, this, "")

    /**
     * The column <code>public.ela_results.detected_ela</code>.
     */
    val DETECTED_ELA: TableField<ElaResultsRecord, Boolean?> = createField(DSL.name("detected_ela"), SQLDataType.BOOLEAN, this, "")

    private constructor(alias: Name, aliased: Table<ElaResultsRecord>?): this(alias, null, null, null, aliased, null, null)
    private constructor(alias: Name, aliased: Table<ElaResultsRecord>?, parameters: Array<Field<*>?>?): this(alias, null, null, null, aliased, parameters, null)
    private constructor(alias: Name, aliased: Table<ElaResultsRecord>?, where: Condition?): this(alias, null, null, null, aliased, null, where)

    /**
     * Create an aliased <code>public.ela_results</code> table reference
     */
    constructor(alias: String): this(DSL.name(alias))

    /**
     * Create an aliased <code>public.ela_results</code> table reference
     */
    constructor(alias: Name): this(alias, null)

    /**
     * Create a <code>public.ela_results</code> table reference
     */
    constructor(): this(DSL.name("ela_results"), null)

    constructor(path: Table<out Record>, childPath: ForeignKey<out Record, ElaResultsRecord>?, parentPath: InverseForeignKey<out Record, ElaResultsRecord>?): this(Internal.createPathAlias(path, childPath, parentPath), path, childPath, parentPath, ELA_RESULTS, null, null)

    /**
     * A subtype implementing {@link Path} for simplified path-based joins.
     */
    open class ElaResultsPath : ElaResults, Path<ElaResultsRecord> {
        constructor(path: Table<out Record>, childPath: ForeignKey<out Record, ElaResultsRecord>?, parentPath: InverseForeignKey<out Record, ElaResultsRecord>?): super(path, childPath, parentPath)
        private constructor(alias: Name, aliased: Table<ElaResultsRecord>): super(alias, aliased)
        override fun `as`(alias: String): ElaResultsPath = ElaResultsPath(DSL.name(alias), this)
        override fun `as`(alias: Name): ElaResultsPath = ElaResultsPath(alias, this)
        override fun `as`(alias: Table<*>): ElaResultsPath = ElaResultsPath(alias.qualifiedName, this)
    }
    override fun getSchema(): Schema? = if (aliased()) null else Public.PUBLIC
    override fun getIdentity(): Identity<ElaResultsRecord, Int?> = super.getIdentity() as Identity<ElaResultsRecord, Int?>
    override fun getPrimaryKey(): UniqueKey<ElaResultsRecord> = ELA_RESULTS_PKEY
    override fun getReferences(): List<ForeignKey<ElaResultsRecord, *>> = listOf(ELA_RESULTS__FK_IMAGE)

    private lateinit var _images: ImagesPath

    /**
     * Get the implicit join path to the <code>public.images</code> table.
     */
    fun images(): ImagesPath {
        if (!this::_images.isInitialized)
            _images = ImagesPath(this, ELA_RESULTS__FK_IMAGE, null)

        return _images;
    }

    val images: ImagesPath
        get(): ImagesPath = images()
    override fun `as`(alias: String): ElaResults = ElaResults(DSL.name(alias), this)
    override fun `as`(alias: Name): ElaResults = ElaResults(alias, this)
    override fun `as`(alias: Table<*>): ElaResults = ElaResults(alias.qualifiedName, this)

    /**
     * Rename this table
     */
    override fun rename(name: String): ElaResults = ElaResults(DSL.name(name), null)

    /**
     * Rename this table
     */
    override fun rename(name: Name): ElaResults = ElaResults(name, null)

    /**
     * Rename this table
     */
    override fun rename(name: Table<*>): ElaResults = ElaResults(name.qualifiedName, null)

    /**
     * Create an inline derived table from this table
     */
    override fun where(condition: Condition?): ElaResults = ElaResults(qualifiedName, if (aliased()) this else null, condition)

    /**
     * Create an inline derived table from this table
     */
    override fun where(conditions: Collection<Condition>): ElaResults = where(DSL.and(conditions))

    /**
     * Create an inline derived table from this table
     */
    override fun where(vararg conditions: Condition?): ElaResults = where(DSL.and(*conditions))

    /**
     * Create an inline derived table from this table
     */
    override fun where(condition: Field<Boolean?>?): ElaResults = where(DSL.condition(condition))

    /**
     * Create an inline derived table from this table
     */
    @PlainSQL override fun where(condition: SQL): ElaResults = where(DSL.condition(condition))

    /**
     * Create an inline derived table from this table
     */
    @PlainSQL override fun where(@Stringly.SQL condition: String): ElaResults = where(DSL.condition(condition))

    /**
     * Create an inline derived table from this table
     */
    @PlainSQL override fun where(@Stringly.SQL condition: String, vararg binds: Any?): ElaResults = where(DSL.condition(condition, *binds))

    /**
     * Create an inline derived table from this table
     */
    @PlainSQL override fun where(@Stringly.SQL condition: String, vararg parts: QueryPart): ElaResults = where(DSL.condition(condition, *parts))

    /**
     * Create an inline derived table from this table
     */
    override fun whereExists(select: Select<*>): ElaResults = where(DSL.exists(select))

    /**
     * Create an inline derived table from this table
     */
    override fun whereNotExists(select: Select<*>): ElaResults = where(DSL.notExists(select))
}
