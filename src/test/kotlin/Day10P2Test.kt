import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Day10P2Test {


    /*
    [({(<(())[]>[[{[]{<()<>> - Complete by adding }}]])})].
[(()[<>])]({[<{<<[]>>( - Complete by adding )}>]}).
(((({<>}<{<{<>}{[]{[]{} - Complete by adding }}>}>)))).
{<[[]]>}<{[{[{[]{()[[[] - Complete by adding ]]}}]}]}>.
<{([{{}}[<[[[<>{}]]]>[]] - Complete by adding ])}>.

}}]])})] - 288957 total points.
)}>]}) - 5566 total points.
}}>}>)))) - 1480781 total points.
]]}}]}]}> - 995444 total points.
])}> - 294 total points.
     */
    @Test
    fun solve_firstSampleInputShouldScore288957Points() {
        assertThat(Day10P2().solve(listOf("[({(<(())[]>[[{[]{<()<>>"))).isEqualTo(288957)
    }

    @Test
    fun solve_secondSampleInputShouldScore5566Points() {
        assertThat(Day10P2().solve(listOf("[(()[<>])]({[<{<<[]>>("))).isEqualTo(5566)
    }
}