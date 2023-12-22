package org.gnit.bible

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.gnit.bible.ui.theme.BibleTheme
import org.gnit.bible.ui.widgets.BibleButton
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.Locale

class BibleState(
    val translationPair: TranslationPair = TranslationPair(Translation.webus, null, ReadingMode.SINGLE),
    val book: Int = 1,
    val chapter:Int = 1,
    val scrollPosition: Int = 0,
    val spaceBetweenVerses: Int = 5
)

class MainActivity : ComponentActivity() {

    lateinit var bibleState: BibleState

    private fun pref() = getSharedPreferences("bible_preference", MODE_PRIVATE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val debugTag = MainActivity::class.simpleName
        Log.d(debugTag, "onCreate() called")

        if(savedInstanceState == null){
            Log.d(debugTag, "Application is loaded for the first time.")
            val language = Locale.getDefault().language
            val translationPair = if (language == Language.ja.name){
                Log.d(debugTag, "Japanese detected, start with Translation:${Translation.jc} with ReadingMode:${ReadingMode.SINGLE}")
                TranslationPair(Translation.jc, null, ReadingMode.SINGLE)
            }else{
                Log.d(debugTag, "Fallback to English, start with Translation:${Translation.webus} with ReadingMode:${ReadingMode.SINGLE}")
                TranslationPair(Translation.webus, null, ReadingMode.SINGLE)
            }

            bibleState = BibleState(translationPair, 1, 1, 0)

            val editor = pref().edit()
            editor.putString(TranslationPair.PREF_KEY, translationPair.toString())
            editor.apply()
        }else{
            //TODO implement the behavior when savedInstanceState is found
        }

        setContent {
            BibleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Bible(bibleState)
                }
            }
        }
    }
}

const val BUTTON_PADDING = 5
const val BUTTON_SIZE = 35
const val BUTTON_ROUND = 5
const val BUTTON_TEXT_FONT_SIZE = 15
const val BUTTON_CONTENT_PADDING = 0

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Bible(bibleState: BibleState, modifier: Modifier = Modifier) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Bible", /*TODO change title to book name chapter & number of choice*/
                        maxLines = 1,
                        overflow = TextOverflow.Clip
                    )
                }

                //TODO implement menu, settings, etc

            )
        },
        content = {
            Box(
                modifier = modifier.absolutePadding(left = 0.dp, top = (BUTTON_SIZE + 30).dp, right = 0.dp, 0.dp)
            ) {
                BibleButton(
                    buttonText = "-",
                    onClick = {Log.d("BibleButton", "button clicked")} ,
                    modifier = Modifier.absolutePadding(left = BUTTON_PADDING.dp).align(Alignment.TopStart)
                )

                var bookSliderPosition by remember { mutableFloatStateOf(0f) }

                Slider(
                    value = bookSliderPosition,
                    onValueChange = {
                        bookSliderPosition =  it
                        Log.d("Slider", "book slider value changed to $it")
                    },
                    steps = 66,
                    valueRange = 1f..66f,
                    thumb = {SliderDefaults.Thumb(interactionSource = remember { MutableInteractionSource() }, modifier = Modifier.width(10.dp).height(10.dp).offset(5.dp, 5.dp)) },
                    modifier = Modifier.align(Alignment.TopCenter).height(BUTTON_SIZE.dp).absolutePadding(top = 1.dp, left = (BUTTON_SIZE + BUTTON_PADDING).dp, right = (BUTTON_SIZE + BUTTON_PADDING).dp,)
                )

                BibleButton(
                    buttonText = "+",
                    onClick = {Log.d("BibleButton", "button clicked")} ,
                    modifier = Modifier.absolutePadding(right = BUTTON_PADDING.dp).align(Alignment.TopEnd)
                )

                when(bibleState.translationPair.readingMode){
                    ReadingMode.SINGLE ->
                        SingleBible(bibleState)
                    ReadingMode.BILINGUAL_SIDE ->
                        BilingualSideBible(bibleState)
                    ReadingMode.BILINGUAL_UNDER ->
                        BilingualUnderBible(bibleState)
                }

                BibleButton(
                    buttonText = "-",
                    onClick = {Log.d("BibleButton", "button clicked")} ,
                    modifier = Modifier.align(Alignment.BottomStart).absolutePadding(left = BUTTON_PADDING.dp, bottom = BUTTON_PADDING.dp)
                )

                var chapterSliderPosition by remember { mutableFloatStateOf(0f) }

                Slider(
                    value = chapterSliderPosition,
                    onValueChange = {
                        chapterSliderPosition = it
                        Log.d("Slider", "chapter slider value changed")
                    },
                    steps = 50,
                    valueRange = 1f..50f,
                    thumb = {SliderDefaults.Thumb(interactionSource = remember { MutableInteractionSource() }, modifier = Modifier.width(10.dp).height(10.dp).offset(5.dp, 5.dp)) },
                    modifier = Modifier.align(Alignment.BottomCenter).height(BUTTON_SIZE.dp).absolutePadding(left = (BUTTON_SIZE + BUTTON_PADDING).dp, right = (BUTTON_SIZE + BUTTON_PADDING).dp, bottom = (BUTTON_PADDING + 4).dp)
                )

                BibleButton(
                    buttonText = "+",
                    onClick = {Log.d("BibleButton", "button clicked")} ,
                    modifier = Modifier.align(Alignment.BottomEnd).absolutePadding(right = BUTTON_PADDING.dp, bottom = BUTTON_PADDING.dp)
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun BiblePreview() {
    BibleTheme {
        Bible(BibleState())
    }
}

@Composable
fun SingleBible(bibleState: BibleState){
    val translation = bibleState.translationPair.mainTranslation
    val book = bibleState.book
    val chapter = bibleState.chapter

    val chapterText = if(translation.isAssets()){
        chapterTextFromAssets(translation = translation, book = book, chapter = chapter)
    } else {
        chapterTextFromDataDir(translation = translation, book = book, chapter = chapter)
    }

    val verses = splitChapterToVerses(chapterText)

    Column(
        modifier = Modifier
            .absolutePadding(
                left = 0.dp,
                top = (BUTTON_SIZE + BUTTON_PADDING).dp,
                right = 0.dp,
                bottom = (BUTTON_SIZE + BUTTON_PADDING).dp
            )
            .verticalScroll(rememberScrollState())
    ) {
        verses.forEachIndexed{ verse, text ->
            Text(
                text = "${verse+1} $text",
                modifier = Modifier.absolutePadding(bottom = bibleState.spaceBetweenVerses.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SingleBiblePreview(){
    BibleTheme {
        SingleBible(bibleState = BibleState())
    }
}

fun splitChapterToVerses(aChapter: String): Array<String> {
    return aChapter.substring(2).split("\\n\\d{1,3} ".toRegex()).toTypedArray()
}

fun addEmptyEntryToMakeSameSize(listA: List<String>, listB: List<String>): Pair<List<String>, List<String>> {
    // Find the longer list
    val longerList = if (listA.size > listB.size) listA else listB
    val shorterList = if (listA.size < listB.size) listA else listB

    // Add empty strings to the shorter list to match the length of the longer list
    val paddedShorterList = shorterList.toMutableList()
    repeat(longerList.size - shorterList.size) {
        paddedShorterList.add("")
    }

    // Preserve original order of listA and listB
    val newListA = if (listA == longerList) longerList else paddedShorterList
    val newListB = if (listA == longerList) paddedShorterList else longerList

    return newListA to newListB
}

@Composable
private fun getVersePairs(bibleState: BibleState): List<Pair<String, String>> {
    val mainTranslation = bibleState.translationPair.mainTranslation
    val subTranslation = bibleState.translationPair.subTranslation
        ?: throw IllegalArgumentException("BilingualSideBible needs subTranslation specified but was null")

    val book = bibleState.book
    val chapter = bibleState.chapter

    val mainChapterText = if (mainTranslation.isAssets()) {
        chapterTextFromAssets(translation = mainTranslation, book = book, chapter = chapter)
    } else {
        chapterTextFromDataDir(translation = mainTranslation, book = book, chapter = chapter)
    }

    val subChapterText = if (subTranslation.isAssets()) {
        chapterTextFromAssets(translation = subTranslation, book = book, chapter = chapter)
    } else {
        chapterTextFromDataDir(translation = subTranslation, book = book, chapter = chapter)
    }

    val mainVerses = splitChapterToVerses(mainChapterText)
    val subVerses = splitChapterToVerses(subChapterText)

    val verses = if (mainVerses.size == subVerses.size) {
        mainVerses.zip(subVerses).toList()
    } else {
        val newPair = addEmptyEntryToMakeSameSize(mainVerses.toList(), subVerses.toList())
        val newMainVerse = newPair.first
        val newSubVerse = newPair.second
        newMainVerse.zip(newSubVerse)
    }
    return verses
}

@Composable
fun BilingualSideBible(bibleState: BibleState) {
    val readingMode = bibleState.translationPair.readingMode
    if (readingMode != ReadingMode.BILINGUAL_SIDE) throw IllegalArgumentException("ReadingMode should be ${ReadingMode.BILINGUAL_SIDE} but trying to put $readingMode")

    val versePairs = getVersePairs(bibleState)

    Column(
        modifier = Modifier
            .absolutePadding(
                left = 0.dp,
                top = (BUTTON_SIZE + BUTTON_PADDING).dp,
                right = 0.dp,
                bottom = (BUTTON_SIZE + BUTTON_PADDING).dp
            )
            .verticalScroll(rememberScrollState())
    ) {
        versePairs.forEachIndexed { verse, pair ->
            Row(
                modifier = Modifier.absolutePadding(bottom = bibleState.spaceBetweenVerses.dp)
            ) {
                Text(
                    text = "${verse+1} ${pair.first}",
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "${verse+1} ${pair.second}",
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BilingualSideBiblePreview(){
    BibleTheme {
        BilingualSideBible(bibleState = BibleState(translationPair = TranslationPair(Translation.jc, Translation.webus, ReadingMode.BILINGUAL_SIDE)))
    }
}

@Composable
fun BilingualUnderBible(bibleState: BibleState) {
    val readingMode = bibleState.translationPair.readingMode
    if (readingMode != ReadingMode.BILINGUAL_UNDER) throw IllegalArgumentException("ReadingMode should be ${ReadingMode.BILINGUAL_UNDER} but trying to put $readingMode")

    val versePairs = getVersePairs(bibleState)

    Column(
        modifier = Modifier
            .absolutePadding(
                left = 0.dp,
                top = (BUTTON_SIZE + BUTTON_PADDING).dp,
                right = 0.dp,
                bottom = (BUTTON_SIZE + BUTTON_PADDING).dp
            )
            .verticalScroll(rememberScrollState())
    ) {
        versePairs.forEachIndexed { verse, pair ->
            Column {
                Text(
                    text = "${verse+1} ${pair.first}"
                )
                Text(
                    text = "${verse+1} ${pair.second}",
                    modifier = Modifier.absolutePadding(bottom = bibleState.spaceBetweenVerses.dp)
                )
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun BilingualUnderBiblePreview(){
    BibleTheme {
        BilingualUnderBible(bibleState = BibleState(translationPair = TranslationPair(Translation.jc, Translation.webus, ReadingMode.BILINGUAL_UNDER)))
    }
}

@Composable
fun chapterTextFromAssets(translation: Translation, book: Int, chapter: Int): String{

    val inputStream = LocalContext.current.assets.open("$translation/$translation.$book.$chapter.txt")
    val inputStreamReader = InputStreamReader(inputStream)
    val bufferedReader = BufferedReader(inputStreamReader)

    val sb = StringBuilder()

    bufferedReader.forEachLine {
        sb.append(it)
        sb.append("\n")
    }
    return sb.toString()
}

@Composable
fun chapterTextFromDataDir(translation: Translation, book: Int, chapter: Int): String{
    //TODO implement logic to open downloaded text files in zip
    return ""
}