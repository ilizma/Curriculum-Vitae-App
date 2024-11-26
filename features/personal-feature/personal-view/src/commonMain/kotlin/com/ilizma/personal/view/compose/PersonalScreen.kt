package com.ilizma.personal.view.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AlternateEmail
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withLink
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.ImageLoader
import coil3.compose.AsyncImage
import coil3.compose.setSingletonImageLoaderFactory
import coil3.request.crossfade
import coil3.util.DebugLogger
import com.ilizma.personal.presentation.model.PersonalDataIntent
import com.ilizma.personal.presentation.model.PersonalDataState
import com.ilizma.personal.presentation.viewmodel.PersonalDataScreenViewModel
import com.ilizma.resources.Res
import com.ilizma.resources.address
import com.ilizma.resources.born_date
import com.ilizma.resources.email
import com.ilizma.resources.phone
import com.ilizma.resources.retry
import com.ilizma.resources.skills
import com.ilizma.resources.ui.theme.Grey
import com.ilizma.resources.ui.theme.Link
import com.ilizma.resources.ui.theme.Main
import com.ilizma.view.lifecycle.collectAsStateMultiplatform
import org.jetbrains.compose.resources.stringResource

@Composable
fun PersonalDataScreen(
    viewModel: PersonalDataScreenViewModel,
    paddingValues: PaddingValues,
    snackbarHostState: SnackbarHostState,
) {
    viewModel.personalDataState
        .collectAsStateMultiplatform(
            initialValue = PersonalDataState.Loading,
        ).value
        .let { state ->
            ScreenState(
                state = state,
                snackbarHostState = snackbarHostState,
                paddingValues = paddingValues,
                onIntent = { viewModel.onIntent(it) }
            )
        }
}

@Composable
private fun ScreenState(
    state: PersonalDataState,
    snackbarHostState: SnackbarHostState,
    paddingValues: PaddingValues,
    onIntent: (PersonalDataIntent) -> Unit,
) {
    when (state) {
        is PersonalDataState.Success -> Content(
            paddingValues = paddingValues,
            state = state,
            onIntent = onIntent
        )

        is PersonalDataState.Error -> ErrorSnackbar(
            paddingValues = paddingValues,
            state = state,
            snackbarHostState = snackbarHostState,
            onRetry = { onIntent(PersonalDataIntent.Retry) },
        )

        PersonalDataState.Loading -> Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
                .padding(paddingValues),
        ) {
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.onPrimary,
            )
        }
    }

}

@Composable
private fun ErrorSnackbar(
    paddingValues: PaddingValues,
    state: PersonalDataState.Error,
    snackbarHostState: SnackbarHostState,
    onRetry: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize()
            .padding(paddingValues),
    ) {
        val message = state.message
        val actionLabel = stringResource(Res.string.retry)
        LaunchedEffect(snackbarHostState) {
            snackbarHostState.showSnackbar(
                message = message,
                actionLabel = actionLabel,
            ).let { snackbarResult ->
                if (snackbarResult == SnackbarResult.ActionPerformed) {
                    onRetry()
                }
            }
        }
    }
}

@Composable
private fun Content(
    paddingValues: PaddingValues,
    state: PersonalDataState.Success,
    onIntent: (PersonalDataIntent) -> Unit,
) {
    setSingletonImageLoaderFactory {
        ImageLoader.Builder(it)
            .crossfade(true)
            .logger(DebugLogger())
            .build()
    }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = Main,
                )
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth()
                        .padding(
                            horizontal = 16.dp,
                            vertical = 8.dp
                        ),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center,
                    ) {
                        AsyncImage(
                            modifier = Modifier.fillMaxWidth()
                                .padding(horizontal = 64.dp)
                                .clip(RoundedCornerShape(8.dp)),
                            contentScale = ContentScale.Crop,
                            model = state.photo,
                            contentDescription = state.name
                        )
                    }
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = state.name,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Text(
                                text = state.surname,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = state.surname2,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
        item {
            Row(
                modifier = Modifier.fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    modifier = Modifier.align(Alignment.CenterVertically),
                    imageVector = Icons.Default.Phone,
                    tint = MaterialTheme.colorScheme.onPrimary,
                    contentDescription = "phone",
                )
                Column(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(Res.string.phone),
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = buildAnnotatedString {
                            withLink(LinkAnnotation.Clickable(tag = state.phone) {
                                onIntent(PersonalDataIntent.Phone(phone = state.phone))
                            }) {
                                withStyle(
                                    style = SpanStyle(
                                        color = Link,
                                        textDecoration = TextDecoration.Underline
                                    )
                                ) {
                                    append(state.phone)
                                }
                            }
                        },
                    )
                    HorizontalDivider(modifier = Modifier.padding(end = 40.dp))
                }
            }
        }
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    modifier = Modifier.align(Alignment.CenterVertically),
                    imageVector = Icons.Default.AlternateEmail,
                    tint = MaterialTheme.colorScheme.onPrimary,
                    contentDescription = "e-mail",
                )
                Column(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(Res.string.email),
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = buildAnnotatedString {
                            withLink(LinkAnnotation.Clickable(tag = state.email) {
                                onIntent(PersonalDataIntent.Email(email = state.email))
                            }) {
                                withStyle(
                                    style = SpanStyle(
                                        color = Link,
                                        textDecoration = TextDecoration.Underline
                                    )
                                ) {
                                    append(state.email)
                                }
                            }
                        },
                    )
                    HorizontalDivider(modifier = Modifier.padding(end = 40.dp))
                }
            }
        }
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    modifier = Modifier.align(Alignment.CenterVertically),
                    imageVector = Icons.Default.LocationOn,
                    tint = MaterialTheme.colorScheme.onPrimary,
                    contentDescription = "address",
                )
                Column(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(Res.string.address),
                        fontWeight = FontWeight.Bold
                    )
                    Text(modifier = Modifier.fillMaxWidth(), text = state.address)
                    Text(modifier = Modifier.fillMaxWidth(), text = state.city)
                    Text(modifier = Modifier.fillMaxWidth(), text = state.postalCode)
                    HorizontalDivider(modifier = Modifier.padding(end = 40.dp))
                }
            }
        }
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    modifier = Modifier.align(Alignment.CenterVertically),
                    imageVector = Icons.Default.CalendarMonth,
                    tint = MaterialTheme.colorScheme.onPrimary,
                    contentDescription = "born data",
                )
                Column(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(Res.string.born_date),
                        fontWeight = FontWeight.Bold
                    )
                    Text(modifier = Modifier.fillMaxWidth(), text = state.bornDate)
                    HorizontalDivider(modifier = Modifier.padding(end = 40.dp))
                }
            }
        }
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    modifier = Modifier.align(Alignment.CenterVertically),
                    imageVector = Icons.Default.Key,
                    tint = MaterialTheme.colorScheme.onPrimary,
                    contentDescription = "skills",
                )
                Column(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(Res.string.skills),
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = state.skills.joinToString(),
                    )
                }
            }
        }
        item {
            Spacer(modifier = Modifier.height(16.dp))
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = Grey,
                )
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth()
                        .padding(
                            horizontal = 16.dp,
                            vertical = 8.dp
                        ), text = state.description
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
        itemsIndexed(
            items = state.other,
            key = { _, it -> it.title },
        ) { index, it ->
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = it.title,
                    fontWeight = FontWeight.Bold,
                )
                if (it.description.isNotBlank()) Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = it.description.replace(oldValue = "\\n", newValue = "\n"),
                )
                if (it.link.isNotBlank()) Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = buildAnnotatedString {
                        withLink(LinkAnnotation.Url(url = it.link)) {
                            withStyle(
                                style = SpanStyle(
                                    color = Link,
                                    textDecoration = TextDecoration.Underline
                                )
                            ) {
                                append(it.link)
                            }
                        }
                    },
                )
                if (index != state.other.lastIndex) HorizontalDivider()
            }
        }
    }
}
