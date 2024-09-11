package com.example.deeplearning

import android.app.LauncherActivity.ListItem
import android.content.ClipDescription
import android.icu.text.CaseMap.Title
import android.os.Bundle
import android.os.Message
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text2.BasicTextField2
import androidx.compose.foundation.text2.input.TextFieldLineLimits
import androidx.compose.foundation.text2.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.ImageNotSupported
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.deeplearning.ui.theme.DeepLearningTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DeepLearningTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                  //  Result()
                  // ProfileCard()
                   // LazyColumnItem()
                  //  HorizontalPagerExample()

                  //  ValuePass()
                  //  Screen()
                   // TextFields()
                  //  TextFieldTwo()
                    ImageLoading()
                }
            }


        }
    }
}

//compose state remember
@Composable
fun Screen(){
    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center){
        val textVisibilityState = remember {
            mutableStateOf(true)
        }
        val textState = remember {
            mutableStateOf("")
        }
        textState.value=if(textVisibilityState.value) "visible text" else "Not visible text"

        Text(
            modifier = Modifier
                .alpha(if(textVisibilityState.value) 1f else 0.3f),
            text = textState.value)

        Spacer(modifier = Modifier.height(50.dp))
        Button(onClick = {
            textVisibilityState.value=!textVisibilityState.value;
        }) {
            Text(text = "Change Visibitlity")

        }

    }
}

//Text field
@Composable
fun TextFields(){
     Column(
         modifier = Modifier.fillMaxSize(),
         horizontalAlignment=Alignment.CenterHorizontally,
         verticalArrangement = Arrangement.Center
     ){
         var inputText by remember {
             mutableStateOf("")
         }
         BasicTextField(
             modifier = Modifier
                 .fillMaxWidth()
                 .padding(horizontal = 20.dp)
                 .clip(RoundedCornerShape(50.dp))
                 .background(MaterialTheme.colorScheme.primary)
                 .padding(16.dp),
             value = inputText,
             onValueChange ={
                 inputText=it
             },
             cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
             maxLines = 1,
             singleLine = true,
             textStyle = TextStyle(
                 color=MaterialTheme.colorScheme.onBackground,
                 fontSize = 18.sp
             ),
             decorationBox = { innerTextField ->
                 Row(
                     modifier = Modifier.fillMaxWidth(),
                     verticalAlignment = Alignment.CenterVertically
                 ){
                     Icon(
                         imageVector = Icons.Default.Search,
                         contentDescription = null
                     )
                     Spacer(modifier = Modifier.width(8.dp))

                     Box(modifier = Modifier.weight(1f)){
                         if(inputText.isEmpty()) {
                             Text(
                                 text = "Search",
                                 color=MaterialTheme.colorScheme.onBackground
                             )
                         }
                         innerTextField()
                     }

                     if(inputText.isNotEmpty()) {
                         Spacer(modifier = Modifier.width(8.dp))
                         Icon(
                             modifier = Modifier.clickable {
                                 inputText = ""
                             },
                             imageVector = Icons.Default.Clear,
                             contentDescription = null
                         )
                     }
                 }
             }
         )
     }
}
//Textfiled2
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TextFieldTwo(){
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment=Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
     var textState= rememberTextFieldState()
        BasicTextField2(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .clip(RoundedCornerShape(50.dp))
                .background(MaterialTheme.colorScheme.primary)
                .padding(16.dp),
         state = textState,
            cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
            lineLimits = TextFieldLineLimits.SingleLine,
            textStyle = TextStyle(
                color=MaterialTheme.colorScheme.onBackground,
                fontSize = 18.sp
            ),
            decorator = { innerTextField ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(8.dp))

                    Box(modifier = Modifier.weight(1f)){
                        if(textState.text.isEmpty()) {
                            Text(
                                text = "Search",
                                color=MaterialTheme.colorScheme.onBackground
                            )
                        }
                        innerTextField()
                    }

                    if(textState.text.isNotEmpty()) {
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(
                            modifier = Modifier.clickable {
                                textState.edit {
                                    this.replace(0, textState.text.length,"")
                                }
                            },
                            imageVector = Icons.Default.Clear,
                            contentDescription = null
                        )
                    }
                }
            }
        )
    }
}

//Image Loading
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ImageLoading(){
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .clip(RoundedCornerShape(30.dp)),
            painter = painterResource(id = R.drawable.car1), contentDescription = "bike"
        )


        val imageUrl="https://www.google.com/search?client=ubuntu-sn&hs=qM3&sca_esv=9783522cabc36d5f&channel=fs&sxsrf=ADLYWILSQMQFlXx9gH3R4z_35vCA5icmGQ:1726046203932&q=car+images&udm=2&fbs=AEQNm0CvspUPonaF8UH5s_LBD3JPX4RSeMPt9v8oIaeGMh2T2D1DyqhnuPxLgMgOaYPYX7OtOF4SxbM4YPsyWUMdeXRPnkQc3caC_NEMjyGZlBqX7YDVSc-lk14rE2h7j-ln6ORWjT4WxqVC6FS82YpEwEqqnkJJKpHqKGrk5ZhbNsOcE3i19GRoFANVfwr_gZS3oWcL17KMyupN4i8_p5OTUvqC1CSN_g&sa=X&ved=2ahUKEwjEsueWx7qIAxU2wjgGHYZuEMYQtKgLegQIDhAB&biw=1366&bih=651&dpr=1#vhid=jHFNCsjXqNGgfM&vssid=mosaic"
         val model=ImageRequest
             .Builder(LocalContext.current)
             .data(imageUrl)
             .size(Size.ORIGINAL)
             .build()
        Spacer(modifier = Modifier.height(50.dp))

        AsyncImage(modifier=Modifier.clip(RoundedCornerShape(20.dp)),
            model =model,
            contentDescription =null )
        val imageState= rememberAsyncImagePainter(model = model).state
        if(imageState is AsyncImagePainter.State.Success){
            Image(
                painter = imageState.painter,
                contentDescription = null
            )
        }
        if(imageState is AsyncImagePainter.State.Loading){
            CircularProgressIndicator(
                modifier=Modifier.size(100.dp),
                color=MaterialTheme.colorScheme.primary
            )

        }
        if(imageState is AsyncImagePainter.State.Error){
            Icon(imageVector = Icons.Rounded.ImageNotSupported, contentDescription =null )
        }

    }
}


@Composable
fun ValuePass(){
    Box(modifier = Modifier
        .fillMaxWidth(0.5f)
        .padding(16.dp)) {
        val painter = painterResource(id = R.drawable.signup)
        val description = "God of war playing"
        val title = "Gello tis my game"
        ImageCard(painter = painter, contentDescription = description, title = title)
    }
}

private val fruitList= listOf(
    "Apple","Orange","Papaya","Mango","PiNeApple",
    "Apple","Orange","Papaya","Mango","PiNeApple",
    "Apple","Orange","Papaya","Mango","PiNeApple",
    "Apple","Orange","Papaya","Mango","PiNeApple","King")
@Composable
fun Result(){
    DeepLearningTheme {
        Surface {
            MyText(data = MyData("Jetpack compose ","Please help me god "))
        }
    }

}
@Composable
fun MyText(data : MyData){
    Row(
        modifier = Modifier.padding(all = 14.dp)
    ) {
        Image(painter = painterResource(R.drawable.thor), contentDescription = "profile",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colorScheme.primary, CircleShape))
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(text = data.name,
                color=MaterialTheme.colorScheme.secondary,
                style=MaterialTheme.typography.titleSmall)

            Spacer(modifier = Modifier.height(4.dp))

            Surface(shape = MaterialTheme.shapes.medium, shadowElevation = 2.dp) {
                Text(text = data.fav,
                    modifier = Modifier.padding(all = 4.dp),
                    style = MaterialTheme.typography.bodyMedium)
            }

        }
    }

}


@Composable
fun ProfileCard(){
    Row(modifier = Modifier.padding(20.dp)){
        Box(modifier = Modifier) {
            Image(
                painter = painterResource(id = R.drawable.thor), contentDescription = "profile",
                modifier = Modifier
                    .size(60.dp)
                    .clip(shape = CircleShape)
                    .border(1.5.dp, MaterialTheme.colorScheme.secondary, CircleShape))
            Icon(imageVector = Icons.Filled.CheckCircle, contentDescription = "checked",
                modifier = Modifier
                    .size(20.dp)
                    .align(Alignment.BottomEnd)
                    .background(Color.Blue))

        }
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(text = "ANDROID")
            Text(text = "5M Subscribers")
        }
    }
}




@Composable
fun LazyColumnItem(){
    LazyColumn(contentPadding = PaddingValues(top=30.dp, bottom = 32.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)) {
//        items(furites.size){
//            index -> LisItems(listName = furites[index])
//
//        }
        items(fruitList){
            LisItems(listName = it)
        }
    }
    
}
@Composable
fun LisItems(listName: String){
    ElevatedCard {
        Text(
            text = listName,
            fontSize = 48.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.Blue)
        )
    }   
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HorizontalPagerExample() {
    Box {


    val pagerState = rememberPagerState(pageCount = {
        10
    })
    HorizontalPager(
        state = pagerState,
        pageSize = PageSize.Fixed(340.dp),
        contentPadding = PaddingValues(horizontal = 64.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .background(Color.Magenta),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Page Index $it", modifier = Modifier)
        }
    }
      
}
}

@Composable
fun ImageCard(
    painter: Painter,
    contentDescription: String,
    title: String,
    modifier:Modifier= Modifier
){
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(15.dp)
   
    ) {
        Box(modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color.Transparent,
                        Color.Black
                    ), startY = 300f
                )
            ))
        Box(modifier = Modifier.height(200.dp)){
            Image(painter = painter, 
                contentDescription =contentDescription,
                contentScale = ContentScale.Crop )

            Box(modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
                contentAlignment = Alignment.BottomStart){
                Text(text = title, style = TextStyle(color = Color.Blue, fontSize = 18.sp))
            }
        
        }
    }
}

