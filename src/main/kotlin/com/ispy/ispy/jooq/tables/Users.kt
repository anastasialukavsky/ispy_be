/*
 * This file is generated by jOOQ.
 */
package com.ispy.ispy.jooq.tables


import com.ispy.ispy.jooq.Public
import com.ispy.ispy.jooq.enums.UserRole
import com.ispy.ispy.jooq.keys.IMAGES__FK_USER
import com.ispy.ispy.jooq.keys.USERS_EMAIL_KEY
import com.ispy.ispy.jooq.keys.USERS_PKEY
import com.ispy.ispy.jooq.tables.Images.ImagesPath
import com.ispy.ispy.jooq.tables.records.UsersRecord

import java.time.LocalDateTime
import java.util.UUID

import kotlin.collections.Collection
import kotlin.collections.List

import org.jooq.Condition
import org.jooq.Field
import org.jooq.ForeignKey
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
open class Users(
    alias: Name,
    path: Table<out Record>?,
    childPath: ForeignKey<out Record, UsersRecord>?,
    parentPath: InverseForeignKey<out Record, UsersRecord>?,
    aliased: Table<UsersRecord>?,
    parameters: Array<Field<*>?>?,
    where: Condition?
): TableImpl<UsersRecord>(
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
         * The reference instance of <code>public.users</code>
         */
        val USERS: Users = Users()
    }

    /**
     * The class holding records for this type
     */
    override fun getRecordType(): Class<UsersRecord> = UsersRecord::class.java

    /**
     * The column <code>public.users.user_id</code>.
     */
    val USER_ID: TableField<UsersRecord, UUID?> = createField(DSL.name("user_id"), SQLDataType.UUID.nullable(false).defaultValue(DSL.field(DSL.raw("gen_random_uuid()"), SQLDataType.UUID)), this, "")

    /**
     * The column <code>public.users.email</code>.
     */
    val EMAIL: TableField<UsersRecord, String?> = createField(DSL.name("email"), SQLDataType.VARCHAR(255), this, "")

    /**
     * The column <code>public.users.password_hash</code>.
     */
    val PASSWORD_HASH: TableField<UsersRecord, String?> = createField(DSL.name("password_hash"), SQLDataType.VARCHAR(255), this, "")

    /**
     * The column <code>public.users.oauth_provider</code>.
     */
    val OAUTH_PROVIDER: TableField<UsersRecord, String?> = createField(DSL.name("oauth_provider"), SQLDataType.VARCHAR(255), this, "")

    /**
     * The column <code>public.users.oauth_id</code>.
     */
    val OAUTH_ID: TableField<UsersRecord, String?> = createField(DSL.name("oauth_id"), SQLDataType.VARCHAR(1024), this, "")

    /**
     * The column <code>public.users.role</code>.
     */
    val ROLE: TableField<UsersRecord, UserRole?> = createField(DSL.name("role"), SQLDataType.VARCHAR.defaultValue(DSL.field(DSL.raw("'USER'::user_role"), SQLDataType.VARCHAR)).asEnumDataType(UserRole::class.java), this, "")

    /**
     * The column <code>public.users.created_at</code>.
     */
    val CREATED_AT: TableField<UsersRecord, LocalDateTime?> = createField(DSL.name("created_at"), SQLDataType.LOCALDATETIME(6).defaultValue(DSL.field(DSL.raw("now()"), SQLDataType.LOCALDATETIME)), this, "")

    /**
     * The column <code>public.users.updated_at</code>.
     */
    val UPDATED_AT: TableField<UsersRecord, LocalDateTime?> = createField(DSL.name("updated_at"), SQLDataType.LOCALDATETIME(6).defaultValue(DSL.field(DSL.raw("now()"), SQLDataType.LOCALDATETIME)), this, "")

    private constructor(alias: Name, aliased: Table<UsersRecord>?): this(alias, null, null, null, aliased, null, null)
    private constructor(alias: Name, aliased: Table<UsersRecord>?, parameters: Array<Field<*>?>?): this(alias, null, null, null, aliased, parameters, null)
    private constructor(alias: Name, aliased: Table<UsersRecord>?, where: Condition?): this(alias, null, null, null, aliased, null, where)

    /**
     * Create an aliased <code>public.users</code> table reference
     */
    constructor(alias: String): this(DSL.name(alias))

    /**
     * Create an aliased <code>public.users</code> table reference
     */
    constructor(alias: Name): this(alias, null)

    /**
     * Create a <code>public.users</code> table reference
     */
    constructor(): this(DSL.name("users"), null)

    constructor(path: Table<out Record>, childPath: ForeignKey<out Record, UsersRecord>?, parentPath: InverseForeignKey<out Record, UsersRecord>?): this(Internal.createPathAlias(path, childPath, parentPath), path, childPath, parentPath, USERS, null, null)

    /**
     * A subtype implementing {@link Path} for simplified path-based joins.
     */
    open class UsersPath : Users, Path<UsersRecord> {
        constructor(path: Table<out Record>, childPath: ForeignKey<out Record, UsersRecord>?, parentPath: InverseForeignKey<out Record, UsersRecord>?): super(path, childPath, parentPath)
        private constructor(alias: Name, aliased: Table<UsersRecord>): super(alias, aliased)
        override fun `as`(alias: String): UsersPath = UsersPath(DSL.name(alias), this)
        override fun `as`(alias: Name): UsersPath = UsersPath(alias, this)
        override fun `as`(alias: Table<*>): UsersPath = UsersPath(alias.qualifiedName, this)
    }
    override fun getSchema(): Schema? = if (aliased()) null else Public.PUBLIC
    override fun getPrimaryKey(): UniqueKey<UsersRecord> = USERS_PKEY
    override fun getUniqueKeys(): List<UniqueKey<UsersRecord>> = listOf(USERS_EMAIL_KEY)

    private lateinit var _images: ImagesPath

    /**
     * Get the implicit to-many join path to the <code>public.images</code>
     * table
     */
    fun images(): ImagesPath {
        if (!this::_images.isInitialized)
            _images = ImagesPath(this, null, IMAGES__FK_USER.inverseKey)

        return _images;
    }

    val images: ImagesPath
        get(): ImagesPath = images()
    override fun `as`(alias: String): Users = Users(DSL.name(alias), this)
    override fun `as`(alias: Name): Users = Users(alias, this)
    override fun `as`(alias: Table<*>): Users = Users(alias.qualifiedName, this)

    /**
     * Rename this table
     */
    override fun rename(name: String): Users = Users(DSL.name(name), null)

    /**
     * Rename this table
     */
    override fun rename(name: Name): Users = Users(name, null)

    /**
     * Rename this table
     */
    override fun rename(name: Table<*>): Users = Users(name.qualifiedName, null)

    /**
     * Create an inline derived table from this table
     */
    override fun where(condition: Condition?): Users = Users(qualifiedName, if (aliased()) this else null, condition)

    /**
     * Create an inline derived table from this table
     */
    override fun where(conditions: Collection<Condition>): Users = where(DSL.and(conditions))

    /**
     * Create an inline derived table from this table
     */
    override fun where(vararg conditions: Condition?): Users = where(DSL.and(*conditions))

    /**
     * Create an inline derived table from this table
     */
    override fun where(condition: Field<Boolean?>?): Users = where(DSL.condition(condition))

    /**
     * Create an inline derived table from this table
     */
    @PlainSQL override fun where(condition: SQL): Users = where(DSL.condition(condition))

    /**
     * Create an inline derived table from this table
     */
    @PlainSQL override fun where(@Stringly.SQL condition: String): Users = where(DSL.condition(condition))

    /**
     * Create an inline derived table from this table
     */
    @PlainSQL override fun where(@Stringly.SQL condition: String, vararg binds: Any?): Users = where(DSL.condition(condition, *binds))

    /**
     * Create an inline derived table from this table
     */
    @PlainSQL override fun where(@Stringly.SQL condition: String, vararg parts: QueryPart): Users = where(DSL.condition(condition, *parts))

    /**
     * Create an inline derived table from this table
     */
    override fun whereExists(select: Select<*>): Users = where(DSL.exists(select))

    /**
     * Create an inline derived table from this table
     */
    override fun whereNotExists(select: Select<*>): Users = where(DSL.notExists(select))
}
