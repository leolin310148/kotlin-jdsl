package com.linecorp.kotlinjdsl.query.spec.predicate

import com.linecorp.kotlinjdsl.query.spec.Froms
import com.linecorp.kotlinjdsl.query.spec.expression.ExpressionSpec
import javax.persistence.criteria.*

data class GreaterThanValueSpec<T, R>(
    private val left: ExpressionSpec<T>,
    private val right: R,
    private val inclusive: Boolean,
) : PredicateSpec where R : Comparable<R>, R : Any, T : R? {
    override fun toCriteriaPredicate(
        froms: Froms,
        query: AbstractQuery<*>,
        criteriaBuilder: CriteriaBuilder
    ): Predicate {
        val leftExpression = left.toCriteriaExpression(froms, query, criteriaBuilder)

        return toCriteriaPredicate(criteriaBuilder, leftExpression)
    }

    override fun toCriteriaPredicate(
        froms: Froms,
        query: CriteriaUpdate<*>,
        criteriaBuilder: CriteriaBuilder
    ): Predicate {
        val leftExpression = left.toCriteriaExpression(froms, query, criteriaBuilder)

        return toCriteriaPredicate(criteriaBuilder, leftExpression)
    }

    override fun toCriteriaPredicate(
        froms: Froms,
        query: CriteriaDelete<*>,
        criteriaBuilder: CriteriaBuilder
    ): Predicate {
        val leftExpression = left.toCriteriaExpression(froms, query, criteriaBuilder)

        return toCriteriaPredicate(criteriaBuilder, leftExpression)
    }

    @Suppress("TYPE_MISMATCH_WARNING")
    private fun toCriteriaPredicate(
        criteriaBuilder: CriteriaBuilder,
        leftExpression: Expression<T>
    ): Predicate {
        return if (inclusive) {
            criteriaBuilder.greaterThanOrEqualTo<R>(leftExpression, right)
        } else {
            criteriaBuilder.greaterThan<R>(leftExpression, right)
        }
    }
}
