package com.linecorp.kotlinjdsl.query.clause.set

import com.linecorp.kotlinjdsl.query.spec.Froms
import com.linecorp.kotlinjdsl.query.spec.expression.ColumnSpec
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaUpdate
import javax.persistence.criteria.Path

/**
 * Internal Only
 * Don't use this directly because it's an <string>INTERNAL</strong>.
 * It does not support backward compatibility.
 */
data class SetClause(private val params: Map<ColumnSpec<*>, Any?>) {
    fun apply(froms: Froms, query: CriteriaUpdate<*>, criteriaBuilder: CriteriaBuilder) {
        params.forEach {
            @Suppress("UNCHECKED_CAST")
            query.set(
                it.key.toCriteriaExpression(froms, query, criteriaBuilder) as Path<Any>,
                it.value
            )
        }
    }
}
