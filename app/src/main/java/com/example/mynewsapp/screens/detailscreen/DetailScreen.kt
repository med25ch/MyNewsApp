package com.example.mynewsapp.screens.detailscreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.mynewsapp.R

@Composable
fun DetailScreen(
    onBack: () -> Unit,
    detailScreenViewModel: DetailScreenViewModel
) {

    val uiState = detailScreenViewModel.uiState.collectAsStateWithLifecycle()


    DisposableEffect(androidx.lifecycle.compose.LocalLifecycleOwner.current) {
        onDispose {
            //detailScreenViewModel.deleteTemporaryArticle()
        }
    }

    if (uiState.value.isLoading) {

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.secondary,
                trackColor = MaterialTheme.colorScheme.surfaceVariant,
            )
        }

    } else {

    ArticleDetails(
        articleTitle = uiState.value.temporaryArticleEntity.title,
        articleUrlImage = uiState.value.temporaryArticleEntity.urlToImage,
        articleBody = uiState.value.temporaryArticleEntity.content,
        onShare = { },
        onBack = onBack)

    }
}


@Composable
fun ArticleDetails(
    modifier: Modifier = Modifier,
    articleTitle: String,
    articleUrlImage: String,
    articleBody: String,
    onShare: () -> Unit,
    onBack: () -> Unit
) {

    Column {
        Row(modifier = modifier.fillMaxWidth()) {

            IconButton(onClick = onBack) {
                Icon(
                    Icons.AutoMirrored.Outlined.ArrowBack,
                    contentDescription = "Localized description"
                )
            }

            Spacer(modifier.weight(1f))

            IconButton(onClick = onShare) {
                Icon(Icons.Outlined.Share, contentDescription = "Localized description")
            }

        }

        Text(text = articleTitle,
            fontWeight = FontWeight.SemiBold,
            modifier = modifier
                .padding(4.dp)
                .clickable {

                }
        )

        AsyncImage(
            model = articleUrlImage,
            contentDescription = "",
            placeholder = painterResource(R.drawable.breaking_news),
            error = painterResource(R.drawable.breaking_news),
            contentScale = ContentScale.Fit,
            modifier = modifier
                .padding(start = 4.dp, end = 4.dp, top = 16.dp, bottom = 16.dp)
                .clip(RoundedCornerShape(8.dp))
        )

        Text(
            text = articleBody,
            fontWeight = FontWeight.Light,
            fontSize = 19.sp,
            modifier = modifier
                .padding(4.dp)
                .verticalScroll(rememberScrollState())
        )
    }


}

@Preview
@Composable
fun ArticleDetailsPreview(){
    //ArticleDetails()
}