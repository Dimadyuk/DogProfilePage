package com.dimadyuk.dogprofilepage

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun ProfilePage(modifier: Modifier = Modifier) {
    Card(
        elevation = CardDefaults.elevatedCardElevation(),
        modifier = modifier
            .fillMaxSize()
            .padding(
                top = 100.dp, start = 16.dp, end = 16.dp, bottom = 100.dp
            )
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            val (image, nameText, countryText, statsRow, buttonFollow, buttonDirect) = createRefs()

            val guideLine = createGuidelineFromTop(0.3f)
            Image(
                modifier = Modifier
                    .size(200.dp)
                    .clip(shape = CircleShape)
                    .border(
                        width = 3.dp,
                        color = Color.Red,
                        shape = CircleShape
                    )
                    .constrainAs(image) {
                        top.linkTo(guideLine)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                contentScale = ContentScale.Crop,
                painter = painterResource(id = R.drawable.dog),
                contentDescription = "dog image"
            )
            Text(
                modifier = Modifier
                    .constrainAs(nameText) {
                        top.linkTo(image.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                text = "Siberian husky",
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Germany",
                modifier = Modifier
                    .constrainAs(countryText) {
                        top.linkTo(nameText.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .constrainAs(statsRow) {
                        top.linkTo(countryText.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                ProfileStats(
                    count = "150",
                    title = "Followers"
                )
                ProfileStats(
                    count = "100",
                    title = "Following"
                )
                ProfileStats(
                    count = "30",
                    title = "Posts"
                )
            }
            Button(
                modifier = Modifier
                    .constrainAs(buttonFollow) {
                        top.linkTo(statsRow.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(buttonDirect.start)
                    },
                onClick = {}
            ) {
                Text(text = "Follow user")
            }
            Button(
                modifier = Modifier
                    .constrainAs(buttonDirect) {
                        top.linkTo(statsRow.bottom)
                        start.linkTo(buttonFollow.end)
                        end.linkTo(parent.end)
                    },
                onClick = {}
            ) {
                Text(text = "Direct message")
            }
        }
    }
}

@Composable
fun ProfileStats(
    modifier: Modifier = Modifier,
    count: String,
    title: String
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = count, fontWeight = FontWeight.Bold)
        Text(text = title)
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePagePreview() {
    ProfilePage()
}
