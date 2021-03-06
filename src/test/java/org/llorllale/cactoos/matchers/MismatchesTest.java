/*
 * The MIT License (MIT)
 *
 * Copyright (c) for portions of project cactoos-matchers are held by
 * Yegor Bugayenko, 2017-2018, as part of project cactoos.
 * All other copyright for project cactoos-matchers are held by
 * George Aristy, 2018-2020.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package org.llorllale.cactoos.matchers;

import org.cactoos.text.Joined;
import org.cactoos.text.TextOf;
import org.junit.jupiter.api.Test;

/**
 * Test case for {@link Mismatches}.
 *
 * @since 1.0.0
 */
@SuppressWarnings("PMD.AvoidDuplicateLiterals")
final class MismatchesTest {

    /**
     * Example of {@link Mismatches} usage.
     */
    @Test
    void example() {
        new Assertion<>(
            "Must mismatch properly",
            new IsText("abc"),
            new Mismatches<>(
                new TextOf("def"),
                "Text with value \"abc\"",
                "was Text with value \"def\""
            )
        ).affirm();
    }

    @Test
    void matches() {
        new Assertion<>(
            "Must mismatch",
            new Mismatches<>(
                new TextOf("def"),
                "Text with value \"abc\"",
                "was Text with value \"def\""
            ),
            new Matches<>(new IsText("abc"))
        ).affirm();
    }

    @Test
    void mismatchesWithWrongMatcher() {
        new Assertion<>(
            "Must mismatch",
            new Mismatches<>(
                new TextOf("def"),
                "Text with value \"abc\"",
                "was Text with value \"def\""
            ),
            new Mismatches<>(
                new IsText("def"),
                new Joined(
                    System.lineSeparator(),
                    "mismatches <def> with message",
                    "<<<",
                    "Expected: Text with value \"abc\"",
                    "     but: was Text with value \"def\"",
                    ">>>"
                ),
                new TextOf("matched <def>")
            )
        ).affirm();
    }

    @Test
    void mismatchesWithWrongMessage() {
        new Assertion<>(
            "Must mismatch",
            new Mismatches<>(
                new TextOf("def"),
                "Text with value \"abc\"",
                "was Text with value \"def\""
            ),
            new Mismatches<>(
                new IsText("ghi"),
                new Joined(
                    System.lineSeparator(),
                    "mismatches <def> with message",
                    "<<<",
                    "Expected: Text with value \"abc\"",
                    "     but: was Text with value \"def\"",
                    ">>>"
                ),
                new Joined(
                    System.lineSeparator(),
                    "mismatched with message",
                    "<<<",
                    "Expected: Text with value \"ghi\"",
                    "     but: was Text with value \"def\"",
                    ">>>"
                )
            )
        ).affirm();
    }
}
