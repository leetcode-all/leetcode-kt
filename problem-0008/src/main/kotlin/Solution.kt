package com.github.alisianoi.problem0008;

import java.lang.NumberFormatException

class Solution {
    private val DIGITS: String = "0123456789";

    enum class ParserState {
        WHITESPACE,
        SIGN,
        HEAD_DIGIT,
        TAIL_DIGIT,
        DONE,
    }

    enum class SignState {
        UNKNOWN,
        POSITIVE,
        NEGATIVE,
    }

    fun myAtoi(str: String): Int {
        var fst = 0
        var lst = 0
        var signState = SignState.UNKNOWN
        var parserState = ParserState.WHITESPACE

        for (i in 0 until str.length) {
            if (parserState == ParserState.DONE) {
                break
            }

            if (parserState == ParserState.WHITESPACE) {
                if (str[i] == ' ') {
                    continue
                } else {
                    parserState = ParserState.SIGN
                }
            }

            if (parserState == ParserState.SIGN) {
                parserState = ParserState.HEAD_DIGIT

                fst = i

                if (str[i] == '+') {
                    signState = SignState.POSITIVE

                    continue
                } else if (str[i] == '-') {
                    signState = SignState.NEGATIVE

                    continue
                } else {
                    signState = SignState.POSITIVE
                }
            }

            if (parserState == ParserState.HEAD_DIGIT) {
                if (!DIGITS.contains(str[i])) {
                    return 0
                }

                parserState = ParserState.TAIL_DIGIT
            }

            if (parserState == ParserState.TAIL_DIGIT) {
                if (!DIGITS.contains(str[i])) {
                    lst = i - 1
                    parserState = ParserState.DONE

                    break
                }
            }
        }

        if (parserState == ParserState.TAIL_DIGIT) {
            lst = str.length - 1

            parserState = ParserState.DONE
        }

        if (parserState != ParserState.DONE) {
            return 0
        }

        return try {
            str.subSequence(fst..lst).toString().toInt()
        } catch (exception: NumberFormatException) {
            when (signState) {
                SignState.POSITIVE -> Int.MAX_VALUE
                SignState.NEGATIVE -> Int.MIN_VALUE
                else -> throw exception
            }
        }
    }
}

fun main() {
    val solution = Solution()
    println(solution.myAtoi("   -12"))
}
