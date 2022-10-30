package com.example.android.wearable.composeforwearos

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.CompactChip
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.edgeSwipeToDismiss
import androidx.wear.compose.material.rememberSwipeToDismissBoxState
import com.example.android.wearable.composeforwearos.theme.WearAppTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import com.google.android.horologist.compose.pager.PagerScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            WearAppTheme {
                val activity: Activity =
                    LocalContext.current as Activity

                val contentList_working = arrayOf(
                    PageInfo.build(
                        activity,
                        R.string.title_2,
                        R.string.description_2
                    ),
                )

                val contentList_not_working = arrayOf(
                    PageInfo.build(
                        activity,
                        R.string.title_1,
                        R.string.description_1
                    ),
                    PageInfo.build(
                        activity,
                        R.string.title_2,
                        R.string.description_2
                    ),
                )

                /* Please switch between the content list from "contentList_not_working"
                 * to "contentList_working" to observe the behaviour, chip is clickable
                 * only on the first page, but not the second page.
                 */
                val contentList = contentList_not_working
                // val contentList = contentList_working

                MyPagerScreen(contentList) {
                    Log.v("PagerActivity", "compact chip is clickable")
                }
            }
        }
    }
}

data class PageInfo(val title: String, val text: String) {
    companion object {
        fun build(ctx: Context, @StringRes titleRes: Int, @StringRes textRes: Int): PageInfo {
            return PageInfo(ctx.getString(titleRes), ctx.getString(textRes))
        }
    }
}

@Composable
fun PageContent(pageInfo : PageInfo,
                          content: @Composable (modifier: Modifier) -> Unit = {}) {
    Box(
        modifier = Modifier.fillMaxSize().padding(8.dp),
        contentAlignment = Alignment.Center,
    ) {
        Column {
            val center: Modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
            Text(modifier = center, text = pageInfo.title, textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.height(10.dp))
            Text(modifier = center, text = pageInfo.text, textAlign = TextAlign.Center)
            // call additional content
            content(center)
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun MyPagerScreen(contents: Array<PageInfo>, onClick: () -> Unit) {
    val swipeDismissState = rememberSwipeToDismissBoxState()
    val pagerState = rememberPagerState()
    val size = contents.size
    PagerScreen(
        modifier = Modifier
            .fillMaxSize()
            .edgeSwipeToDismiss(swipeDismissState),
        count = size,
        state = pagerState
    ) { page ->
        PageContent(
            pageInfo = contents[page],
        ) { modifier ->
            if (page == size - 1) {
                CompactChip(
                    modifier = modifier.padding(top = 8.dp),
                    onClick = onClick,
                    label = {
                        Text(
                            text = "Click Me",
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                )
            }
        }
    }
}