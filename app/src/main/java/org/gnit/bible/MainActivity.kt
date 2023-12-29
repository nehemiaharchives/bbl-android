package org.gnit.bible

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.calculateZoom
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.ConfigurationCompat
import androidx.lifecycle.compose.LifecycleResumeEffect
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.gnit.bible.ui.theme.BibleTheme
import org.gnit.bible.ui.widgets.BIBLE_VIEW_ICON
import org.gnit.bible.ui.widgets.BIBLE_VIEW_ICON_SPACER
import org.gnit.bible.ui.widgets.BibleButton
import org.gnit.bible.ui.widgets.DROPDOWN_MENU_HEIGHT
import org.gnit.bible.ui.widgets.DROPDOWN_MENU_ITEM_LEFT_PADDING
import org.gnit.bible.ui.widgets.DROPDOWN_MENU_ITEM_RIGHT_PADDING
import org.gnit.bible.ui.widgets.DROPDOWN_MENU_WIDTH
import org.gnit.bible.ui.widgets.TranslationDropDownMenuItem
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.Locale
import kotlin.math.roundToInt

@Serializable
@Parcelize
data class BibleState(
    val mainTranslation: Translation = Translation.webus,
    val subTranslation: Translation? = null,
    val readingMode: ReadingMode = ReadingMode.SINGLE,
    val book: Int = 1,
    val chapter: Int = 1,
    val fontSize: Int = 16,
    val scrollPercent: Float = 0f,
    val isZebraBackground: Boolean = false,
    val spaceBetweenVerses: Int = SPACE_BETWEEN_VERSES_MIN,
    val isFontFamilySerif: Boolean = true
): Parcelable{
    fun prevBook() = copy(book = book - 1, chapter = 1)
    fun nextBook() = copy(book = book + 1, chapter = 1)
    fun changeBook(newBook: Int) = copy(book = newBook, chapter = 1)
    fun prevChapter() = copy(chapter = chapter - 1)
    fun nextChapter() = copy(chapter = chapter + 1)
    fun isLastChapter() = (chapter == Books.maxChapter(book))
    fun lastChapter() = Books.maxChapter(book)
    fun describeBookChapter() = "${mainTranslation.books[book]} $chapter"
    fun isSingleMain(translationToCompare: Translation) = (readingMode == ReadingMode.SINGLE && mainTranslation == translationToCompare)
    fun isSideMain(translationToCompare: Translation) = (readingMode == ReadingMode.BILINGUAL_SIDE && mainTranslation == translationToCompare)
    fun isSideMainOrSub(translationToCompare: Translation) = (readingMode == ReadingMode.BILINGUAL_SIDE && (mainTranslation == translationToCompare || subTranslation == translationToCompare))
    fun isUnderMain(translationToCompare: Translation) = (readingMode == ReadingMode.BILINGUAL_UNDER && mainTranslation == translationToCompare)
    fun isUnderMainOrSub(translationToCompare: Translation) = (readingMode == ReadingMode.BILINGUAL_UNDER && (mainTranslation == translationToCompare || subTranslation == translationToCompare))
    fun narrowerSpaceBetweenVerses() = copy(spaceBetweenVerses = spaceBetweenVerses -1)
    fun widerSpaceBetweenVerses() = copy(spaceBetweenVerses = spaceBetweenVerses + 1)

    companion object { val json = Json { encodeDefaults = true }}
    fun toJson() = json.encodeToString(this)
}

fun String.toBibleState() = BibleState.json.decodeFromString<BibleState>(this)

const val SPACE_BETWEEN_VERSES_MIN = 5
const val SPACE_BETWEEN_VERSES_MAX = 50

const val SHARED_PREFERENCE_NAME = "Bible"
const val SHARED_PREFERENCE_KEY_BIBLE_STATE = "bible_state"



@Composable
fun rememberBibleState(): BibleState {



    lateinit var initialBibleState: BibleState

    val sharedPreferences = LocalContext.current.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)
    val bibleStateJson = sharedPreferences.getString(SHARED_PREFERENCE_KEY_BIBLE_STATE, null)
    if (bibleStateJson != null){
        initialBibleState = bibleStateJson.toBibleState()
        Log.d("Bible Lifecycle", "sharedPreferences had initialBibleState: $initialBibleState")
    } else {
        val configuration = LocalConfiguration.current
        val defaultLocale = ConfigurationCompat.getLocales(configuration)[0]?: Locale.ENGLISH
        val defaultLanguage = defaultLocale.language
        Log.d("rememberBibleSate", "default language is $defaultLanguage")

        val listOfLanguages = getAvailableTranslations().map { it.language }
        val bibleLanguage = listOfLanguages.firstOrNull { language -> language.toString() == defaultLanguage }?: Language.en
        Log.d("rememberBibleSate", "bibleLanguage is determined to $bibleLanguage")

        val initialMainTranslation = bibleLanguage.defaultTranslation()
        initialBibleState = BibleState(mainTranslation = initialMainTranslation)
        Log.d("Bible Lifecycle", "sharedPreferences was null, computed initialBibleState: $initialBibleState")
    }

    return initialBibleState
}

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("Bible Lifecycle", "here is on create")

        setContent {
            BibleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Bible()
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

fun getAvailableTranslations() = Translation.entries.filter { it.isAssets() } //TODO add downloaded translations

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Bible(modifier: Modifier = Modifier) {

    val initialBibleState = rememberBibleState()
    var bibleState by rememberSaveable { mutableStateOf(initialBibleState) }

    Log.d("Bible Lifecycle", "by rememberSaveable { mutableStateOf(initialBibleState) } called, bibleState:$bibleState")

    var bibleTitle by rememberSaveable { mutableStateOf(bibleState.describeBookChapter()) }
    var zoom by remember { mutableFloatStateOf(bibleState.fontSize.toFloat()) }

    val sharedPreferences = LocalContext.current.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)
    val lifecycleOwner = LocalLifecycleOwner.current
    LifecycleResumeEffect(lifecycleOwner){
        onPauseOrDispose {
            Log.d("Bible Lifecycle", "here is on pause, saving bibleState: $bibleState")
            sharedPreferences.edit().putString(SHARED_PREFERENCE_KEY_BIBLE_STATE, bibleState.toJson()).apply()
        }
    }

    Scaffold(
        topBar = {

            var menuExpanded by remember { mutableStateOf(false) }

            TopAppBar(
                modifier = Modifier
                    .height(55.dp)
                    .padding(vertical = 15.dp),
                title = {
                    Text(
                        text = bibleTitle,
                        fontFamily = if (bibleState.isFontFamilySerif) bibleState.mainTranslation.language.serifFontFamily() else bibleState.mainTranslation.language.sansFontFamily(),
                        maxLines = 1,
                        overflow = TextOverflow.Clip
                    )
                },
                actions = {
                    IconButton(onClick = { menuExpanded = !menuExpanded }) {
                        Icon(
                            imageVector = Icons.Filled.MoreVert,
                            contentDescription = "menu"
                        )
                    }

                    var settingExpanded by remember { mutableStateOf(false) }

                    DropdownMenu(expanded = menuExpanded, onDismissRequest = { menuExpanded = false }) {
                        getAvailableTranslations().forEach {translationItem ->
                            TranslationDropDownMenuItem(
                                settingExpanded,
                                bibleState,
                                translationItem,
                                onClickSingleIcon = {
                                    if(bibleState.readingMode == ReadingMode.SINGLE && bibleState.mainTranslation != translationItem){
                                        Log.d("DropDownMenu", "$translationItem is selected, this will change mainTranslation in SingleView")
                                        bibleState = bibleState.copy(mainTranslation = translationItem)
                                        bibleTitle = bibleState.describeBookChapter()
                                        menuExpanded = false
                                        Log.d("DropdownMenuItem", "mainTranslation changed $bibleState")
                                    }else if(bibleState.readingMode != ReadingMode.SINGLE){
                                        Log.d("DropDownMenu", "Reading Mode will be changed from Bilingual(Side|Under) to Single. mainTranslation will be changed. subTranslation will be null")
                                        bibleState = bibleState.copy(mainTranslation = translationItem, subTranslation = null, readingMode = ReadingMode.SINGLE)
                                        bibleTitle = bibleState.describeBookChapter()
                                        menuExpanded = false
                                    }
                                },
                                onClickSideIcon = {
                                    if(bibleState.isSingleMain(translationItem)){
                                        Log.d("DropDownMenu", "in SingleView, no action should be taken when clicking side icon")
                                    }else{
                                        Log.d("DropDownMenu", "$translationItem will be added to subTranslation, and ReadingMode will be changed to SIDE")
                                        bibleState = bibleState.copy(subTranslation = translationItem, readingMode = ReadingMode.BILINGUAL_SIDE)
                                        bibleTitle = bibleState.describeBookChapter()
                                        menuExpanded = false
                                    }
                                },
                                onClickUnderIcon = {
                                    if(bibleState.isSingleMain(translationItem)){
                                        Log.d("DropDownMenu", "in SingleView, no action should be taken when clicking under icon")
                                    }else{
                                        Log.d("DropDownMenu", "$translationItem will be added to subTranslation, and ReadingMode will be changed to UNDER")
                                        bibleState = bibleState.copy(subTranslation = translationItem, readingMode = ReadingMode.BILINGUAL_UNDER)
                                        bibleTitle = bibleState.describeBookChapter()
                                        menuExpanded = false
                                    }
                                }
                            )
                        }

                        Box(modifier = modifier
                            .height(DROPDOWN_MENU_HEIGHT.dp)
                            .width(DROPDOWN_MENU_WIDTH.dp)
                            .absolutePadding(
                                left = DROPDOWN_MENU_ITEM_LEFT_PADDING.dp,
                                right = DROPDOWN_MENU_ITEM_RIGHT_PADDING.dp
                            )
                        ) {
                            Row(modifier.align(Alignment.CenterEnd)) {
                                if (settingExpanded){

                                    Icon(
                                        imageVector = ImageVector.vectorResource(id = R.drawable.font_switch),
                                        contentDescription = "Switch FontFamily between Serif and SansSerif",
                                        modifier = modifier
                                            .size(BIBLE_VIEW_ICON.dp)
                                            .clickable {
                                                bibleState =
                                                    bibleState.copy(isFontFamilySerif = !bibleState.isFontFamilySerif)
                                            },
                                        tint = MaterialTheme.colorScheme.secondary
                                    )

                                    Spacer(modifier = Modifier.width(BIBLE_VIEW_ICON_SPACER.dp))

                                    Icon(
                                        imageVector = ImageVector.vectorResource(id = R.drawable.arrows_collapse),
                                        contentDescription = "Narrower space between verses",
                                        modifier = modifier
                                            .size(BIBLE_VIEW_ICON.dp)
                                            .clickable {
                                                if (bibleState.spaceBetweenVerses != SPACE_BETWEEN_VERSES_MIN) bibleState =
                                                    bibleState.narrowerSpaceBetweenVerses()
                                            },
                                        tint = MaterialTheme.colorScheme.secondary
                                    )

                                    Spacer(modifier = Modifier.width(BIBLE_VIEW_ICON_SPACER.dp))

                                    Icon(
                                        imageVector = ImageVector.vectorResource(id = R.drawable.arrows_expand),
                                        contentDescription = "Wider space between verses",
                                        modifier = modifier
                                            .size(BIBLE_VIEW_ICON.dp)
                                            .clickable {
                                                if (bibleState.spaceBetweenVerses != SPACE_BETWEEN_VERSES_MAX) bibleState =
                                                    bibleState.widerSpaceBetweenVerses()
                                            },
                                        tint = MaterialTheme.colorScheme.secondary
                                    )

                                    Spacer(modifier = Modifier.width(BIBLE_VIEW_ICON_SPACER.dp))

                                    Icon(
                                        imageVector = ImageVector.vectorResource(id = R.drawable.rows_white),
                                        contentDescription = "Rows with plain background",
                                        modifier = modifier
                                            .size(BIBLE_VIEW_ICON.dp)
                                            .clickable {
                                                bibleState =
                                                    bibleState.copy(isZebraBackground = false)
                                            },
                                        tint = if (bibleState.isZebraBackground) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.primary
                                    )

                                    Spacer(modifier = Modifier.width(BIBLE_VIEW_ICON_SPACER.dp))

                                    Icon(
                                        imageVector = ImageVector.vectorResource(id = R.drawable.rows_zebra),
                                        contentDescription = "Rows with zebra background",
                                        modifier = modifier
                                            .size(BIBLE_VIEW_ICON.dp)
                                            .clickable {
                                                bibleState =
                                                    bibleState.copy(isZebraBackground = true)
                                            },
                                        tint = if (bibleState.isZebraBackground) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
                                    )

                                    Spacer(modifier = Modifier.width(BIBLE_VIEW_ICON_SPACER.dp))

                                }

                                Icon(
                                    imageVector = ImageVector.vectorResource(id = R.drawable.settings),
                                    contentDescription = "Settings",
                                    modifier = modifier
                                        .size(BIBLE_VIEW_ICON.dp)
                                        .clickable { settingExpanded = !settingExpanded },
                                    tint = if (settingExpanded) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
                                )
                            }
                        }
                    }
                }
                //TODO implement menu, settings, etc

            )
        },
        content = {
            Box(
                modifier = modifier
                    .absolutePadding(left = 0.dp, top = (BUTTON_SIZE + 15).dp, right = 0.dp, 0.dp)
                    .pointerInput(Unit) {
                        awaitEachGesture {
                            awaitFirstDown()
                            do {
                                val event = awaitPointerEvent()
                                val oldZoom = zoom

                                if (5f < zoom && zoom < 400f) {

                                    zoom *= event.calculateZoom()

                                    if(oldZoom != zoom){
                                        Log.d(
                                            "Scaffold.content Box",
                                            "zoom event detected. oldZoom:$oldZoom, new zoom = $zoom"
                                        )
                                        val intZoomValue = zoom.roundToInt()
                                        if (bibleState.fontSize != intZoomValue) {
                                            bibleState = bibleState.copy(fontSize = intZoomValue)
                                            Log.d(
                                                "Scaffold.content Box",
                                                "fontSize changed $bibleState"
                                            )
                                        }
                                    }

                                } else if (zoom > 400f) {
                                    zoom = 399.9f
                                    Log.d(
                                        "Scaffold.content Box",
                                        "zoom $zoom is more than 400, no more zooming"
                                    )
                                } else if (zoom < 5f) {
                                    zoom = 5.1f
                                    Log.d(
                                        "Scaffold.content Box",
                                        "zoom $zoom is smaller than 5, no more zooming"
                                    )
                                }


                            } while (event.changes.any { it.pressed })
                        }
                    }
            ) {

                var bookSliderPosition by remember { mutableFloatStateOf(bibleState.book.toFloat()) }
                var chapterSliderPosition by remember { mutableFloatStateOf(bibleState.chapter.toFloat()) }

                BibleButton(
                    buttonText = "-",
                    onClick = {
                        if(bibleState.book != 1){
                            bookSliderPosition--
                            chapterSliderPosition = 1f
                            bibleState = bibleState.prevBook()
                            bibleTitle = bibleState.describeBookChapter()
                            Log.d("BibleButton", "book changed $bibleState")
                        }
                    } ,
                    modifier = Modifier
                        .absolutePadding(left = BUTTON_PADDING.dp)
                        .align(Alignment.TopStart)
                )

                Slider(
                    value = bookSliderPosition,
                    onValueChange = {
                        bookSliderPosition = it
                        chapterSliderPosition = 1f
                        bibleState = bibleState.changeBook(it.roundToInt())
                        bibleTitle = bibleState.describeBookChapter()
                        Log.d("Slider", "book changed $bibleState")
                    },
                    steps = 66,
                    valueRange = 1f..66f,
                    thumb = {SliderDefaults.Thumb(interactionSource = remember { MutableInteractionSource() }, modifier = Modifier
                        .width(10.dp)
                        .height(10.dp)
                        .offset(5.dp, 5.dp)) },
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .height(BUTTON_SIZE.dp)
                        .absolutePadding(
                            top = 1.dp,
                            left = (BUTTON_SIZE + BUTTON_PADDING).dp,
                            right = (BUTTON_SIZE + BUTTON_PADDING).dp,
                        )
                )

                BibleButton(
                    buttonText = "+",
                    onClick = {
                        if (bibleState.book != 66){
                            bookSliderPosition++
                            chapterSliderPosition = 1f
                            bibleState = bibleState.nextBook()
                            bibleTitle = bibleState.describeBookChapter()
                            Log.d("BibleButton", "book changed $bibleState")
                        }
                    },
                    modifier = Modifier
                        .absolutePadding(right = BUTTON_PADDING.dp)
                        .align(Alignment.TopEnd)
                )

                when(bibleState.readingMode){
                    ReadingMode.SINGLE ->
                        SingleBible(bibleState)
                    ReadingMode.BILINGUAL_SIDE ->
                        BilingualSideBible(bibleState)
                    ReadingMode.BILINGUAL_UNDER ->
                        BilingualUnderBible(bibleState)
                }

                BibleButton(
                    buttonText = "-",
                    onClick = {
                        if(bibleState.chapter != 1){
                            chapterSliderPosition--
                            bibleState = bibleState.prevChapter()
                            bibleTitle = bibleState.describeBookChapter()
                            Log.d("BibleButton", "chapter changed $bibleState")
                        }
                    },
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .absolutePadding(left = BUTTON_PADDING.dp, bottom = BUTTON_PADDING.dp)
                )

                Slider(
                    value = chapterSliderPosition,
                    onValueChange = {
                        chapterSliderPosition = it
                        bibleState = bibleState.copy(chapter = it.roundToInt())
                        bibleTitle = bibleState.describeBookChapter()
                        Log.d("Slider", "chapter slider value changed to $bibleState.chapter")
                    },
                    steps = bibleState.lastChapter(),
                    valueRange = 1f..bibleState.lastChapter().toFloat(),
                    thumb = {SliderDefaults.Thumb(interactionSource = remember { MutableInteractionSource() }, modifier = Modifier
                        .width(10.dp)
                        .height(10.dp)
                        .offset(5.dp, 5.dp)) },
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .height(BUTTON_SIZE.dp)
                        .absolutePadding(
                            left = (BUTTON_SIZE + BUTTON_PADDING).dp,
                            right = (BUTTON_SIZE + BUTTON_PADDING).dp,
                            bottom = (BUTTON_PADDING + 4).dp
                        )
                )

                BibleButton(
                    buttonText = "+",
                    onClick = {
                        if(!bibleState.isLastChapter()){
                            chapterSliderPosition++
                            bibleState = bibleState.nextChapter()
                            bibleTitle = bibleState.describeBookChapter()
                            Log.d("BibleButton", "chapter changed $bibleState")
                        }
                    },
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .absolutePadding(right = BUTTON_PADDING.dp, bottom = BUTTON_PADDING.dp)
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun BiblePreview() {
    BibleTheme {
        Bible()
    }
}

const val VERSES_COLUMN_FILL_MAX_HEIGHT = 0.999f

fun Int.isEven() = this % 2 == 0

@Composable
fun SingleBible(bibleState: BibleState){

    Log.d("SingleBible", "bibleState: $bibleState")

    val translation = bibleState.mainTranslation
    val book = bibleState.book
    val chapter = bibleState.chapter

    val chapterText = if(translation.isAssets()){
        chapterTextFromAssets(translation = translation, book = book, chapter = chapter)
    } else {
        chapterTextFromDataDir(translation = translation, book = book, chapter = chapter)
    }

    val verses = splitChapterToVerses(chapterText)
    val scrollState = rememberScrollState()

    ScrollableColumn(bibleState, scrollState) {
        verses.forEachIndexed{ verse, text ->

            val background = if(bibleState.isZebraBackground && verse.isEven()) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.background

            Text(
                text = "${verse+1} $text",
                style = TextStyle(fontSize = bibleState.fontSize.sp, fontFamily = if (bibleState.isFontFamilySerif) translation.language.serifFontFamily() else translation.language.sansFontFamily()),
                modifier = Modifier
                    .absolutePadding(bottom = bibleState.spaceBetweenVerses.dp)
                    .background(background)
                    .fillMaxWidth()
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
    val mainTranslation = bibleState.mainTranslation
    val subTranslation = bibleState.subTranslation?: throw IllegalArgumentException("subTranslation is required but null")

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
    val readingMode = bibleState.readingMode
    if (readingMode != ReadingMode.BILINGUAL_SIDE) throw IllegalArgumentException("ReadingMode should be ${ReadingMode.BILINGUAL_SIDE} but trying to put $readingMode")
    if (bibleState.subTranslation == null) throw IllegalArgumentException("ReadingMode should be ${ReadingMode.BILINGUAL_SIDE} so subTranslation is needed but null")

    val versePairs = getVersePairs(bibleState)
    val scrollState = rememberScrollState()

    ScrollableColumn(bibleState, scrollState) {
        versePairs.forEachIndexed { verse, pair ->

            val background = if(bibleState.isZebraBackground && verse.isEven()) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.background

            Row(
                modifier = Modifier
                    .absolutePadding(bottom = bibleState.spaceBetweenVerses.dp)
                    .background(background)
            ) {
                Text(
                    text = "${verse+1} ${pair.first}",
                    style = TextStyle(fontSize = bibleState.fontSize.sp, fontFamily = if (bibleState.isFontFamilySerif) bibleState.mainTranslation.language.serifFontFamily() else bibleState.mainTranslation.language.sansFontFamily()),
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "${verse+1} ${pair.second}",
                    style = TextStyle(fontSize = bibleState.fontSize.sp, fontFamily = if (bibleState.isFontFamilySerif) bibleState.subTranslation.language.serifFontFamily() else bibleState.subTranslation.language.sansFontFamily()),
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

val sideView = BibleState(Translation.jc, Translation.webus, ReadingMode.BILINGUAL_SIDE)

@Preview(showBackground = true)
@Composable
fun BilingualSideBiblePreview(){
    BibleTheme {
        BilingualSideBible(bibleState = sideView)
    }
}

@Composable
fun BilingualUnderBible(bibleState: BibleState) {
    val readingMode = bibleState.readingMode
    if (readingMode != ReadingMode.BILINGUAL_UNDER) throw IllegalArgumentException("ReadingMode should be ${ReadingMode.BILINGUAL_UNDER} but trying to put $readingMode")
    if (bibleState.subTranslation == null) throw IllegalArgumentException("ReadingMode should be ${ReadingMode.BILINGUAL_UNDER} so subTranslation is needed but null")

    val versePairs = getVersePairs(bibleState)
    val scrollState = rememberScrollState()

    ScrollableColumn(bibleState, scrollState) {
        versePairs.forEachIndexed { verse, pair ->

            val background = if(bibleState.isZebraBackground && verse.isEven()) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.background

            Column(modifier = Modifier.background(background)) {
                Text(
                    text = "${verse+1} ${pair.first}",
                    style = TextStyle(fontSize = bibleState.fontSize.sp, fontFamily = if (bibleState.isFontFamilySerif) bibleState.mainTranslation.language.serifFontFamily() else bibleState.mainTranslation.language.sansFontFamily()),
                )
                Text(
                    text = "${verse+1} ${pair.second}",
                    style = TextStyle(fontSize = bibleState.fontSize.sp, fontFamily = if (bibleState.isFontFamilySerif) bibleState.subTranslation.language.serifFontFamily() else bibleState.subTranslation.language.sansFontFamily()),
                    modifier = Modifier.absolutePadding(bottom = bibleState.spaceBetweenVerses.dp)
                )
            }
        }
    }
}

val downView = BibleState(Translation.jc, Translation.webus, ReadingMode.BILINGUAL_UNDER)

@Preview(showBackground = true)
@Composable
fun BilingualUnderBiblePreview(){
    BibleTheme {
        BilingualUnderBible(bibleState = downView)
    }
}

@Composable
fun ScrollableColumn(
    bibleState: BibleState,
    scrollState: ScrollState,
    content: @Composable () -> Unit
) {
    Column(
        modifier = Modifier.absolutePadding(
            top = (BUTTON_SIZE + BUTTON_PADDING * 2).dp,
            bottom = (BUTTON_SIZE + BUTTON_PADDING).dp
        )
            .offset(y = (-BUTTON_PADDING).dp)
            .fillMaxHeight(VERSES_COLUMN_FILL_MAX_HEIGHT)
            .verticalScroll(scrollState)
    ){
        content()
    }
    LaunchedEffect(bibleState.book) {
        scrollState.scrollTo(0)
    }
    LaunchedEffect(bibleState.chapter) {
        scrollState.scrollTo(0)
    }

    LaunchedEffect(bibleState.readingMode){
        val scrollValue = (scrollState.maxValue * bibleState.scrollPercent).toInt()
        scrollState.scrollTo(scrollValue)
    }

    val sharedPreferences = LocalContext.current.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)
    LaunchedEffect(scrollState){
        val lastScrollValue = scrollState.value
        var pendingSaveJob: Job? = null

        snapshotFlow { scrollState.value }
            .collect{ newValue ->
                if(newValue != lastScrollValue) {
                    pendingSaveJob?.cancel()
                    pendingSaveJob = launch {
                        delay(200)
                        if (!scrollState.isScrollInProgress){
                            val scrollPercent = computeScrollPercent(newValue, scrollState)
                            sharedPreferences.edit().putString(SHARED_PREFERENCE_KEY_BIBLE_STATE, bibleState.copy(scrollPercent = scrollPercent).toJson()).apply()
                            Log.d("ScrollableColumn", "Saved scroll scrollPercent: $scrollPercent")
                        }
                    }
                }
            }
    }
}

private fun computeScrollPercent(scrollValue: Int, scrollState: ScrollState): Float {
    val totalScrollableHeight = scrollState.maxValue
    return scrollValue.toFloat() / totalScrollableHeight
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