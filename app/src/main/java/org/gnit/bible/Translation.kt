package org.gnit.bible

import android.util.SparseArray
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import kotlinx.serialization.Serializable
import org.gnit.bible.lib.TranslationSerializer
import org.gnit.bible.ui.widgets.enSansFontFamily
import org.gnit.bible.ui.widgets.enSerifFontFamily
import org.gnit.bible.ui.widgets.jaSansFontFamily
import org.gnit.bible.ui.widgets.jaSerifFontFamily
import org.gnit.bible.ui.widgets.koSansFontFamily
import org.gnit.bible.ui.widgets.koSerifFontFamily
import org.gnit.bible.ui.widgets.scSansFontFamily
import org.gnit.bible.ui.widgets.scSerifFontFamily

enum class Language {
    en, es, pt, de, fr, ru, nl, it, pl, uk, sv, zh, ko, ja, vi;

    fun defaultTranslation() = when(this){
        en -> Translation.webus
        es -> Translation.rvr09
        pt -> Translation.tb
        de -> Translation.delut
        fr -> Translation.lsg
        ru -> Translation.sinod
        nl -> Translation.svrj
        it -> Translation.rdv24
        pl -> Translation.ubg
        uk -> Translation.ubio
        sv -> Translation.sven
        zh -> Translation.cunp
        ko -> Translation.krv
        ja -> Translation.jc
        vi -> Translation.kttv
    }

    fun serifFontFamily() = when(this){
        en, es, pt, de, fr, ru, nl, it, pl, uk, sv, vi -> enSerifFontFamily
        zh -> scSerifFontFamily
        ko -> koSerifFontFamily
        ja -> jaSerifFontFamily
    }

    fun sansFontFamily() = when(this){
        en, es, pt, de, fr, ru, nl, it, pl, uk, sv, vi -> enSansFontFamily
        zh -> scSansFontFamily
        ko -> koSansFontFamily
        ja -> jaSansFontFamily
    }
}

@Serializable(with = TranslationSerializer::class)
enum class Translation(val language: Language, val year: Int, val books: SparseArray<String>, val nativeName: String, val defaultSortOrder: Int) {
    // English World
    webus(Language.en, 2000, Books.ENGLISH_NUMBER_NAME_MAP, "World English Bible", 1), // English
    kjv(Language.en, 1611, Books.ENGLISH_NUMBER_NAME_MAP, "King James Version", 2), // English

    // Latin America
    rvr09(Language.es, 1909, Books.SPANISH_NUMBER_NAME_MAP, "Reina Valera", 3), // Spanish
    tb(Language.pt, 1917, Books.PORTUGUESE_NUMBER_NAME_MAP, "Tradução Brasileira", 4), // Portuguese

    // Europe
    delut(Language.de, 1912, Books.GERMAN_NUMBER_NAME_MAP, "Lutherbibel", 5), // German

    // UK already included in English above
    lsg(Language.fr, 1910, Books.FRENCH_NUMBER_NAME_MAP, "Louis Segond Bible", 6), //French
    sinod(Language.ru, 1876, Books.RUSSIAN_NUMBER_NAME_MAP, "Синодальный перевод", 7), //Russian
    svrj(Language.nl, 1888, Books.DUTCH_NUMBER_NAME_MAP, "Statenvertaling Jongbloed-editie", 8), //Dutch
    rdv24(Language.it, 1924, Books.ITALIAN_NUMBER_NAME_MAP, "Versione Diodati Riveduta", 9), //Italian,

    // Spain  already included in Spanish above
    ubg(Language.pl, 2017, Books.POLISH_NUMBER_NAME_MAP, "Uwspółcześniona Biblia gdańska", 10), //Polish
    ubio(Language.uk, 1962, Books.UKRAINIAN_NUMBER_NAME_MAP, "Біблія в пер. Івана Огієнка", 11), //Ukrainian
    sven(Language.sv, 1917, Books.SWEDISH_NUMBER_NAME_MAP, "1917 års kyrkobibel", 12), //Swedish

    // North East Asia
    cunp(Language.zh, 1919, Books.CHINESE_NUMBER_NAME_MAP, "和合本", 13), // Chinese, Chinese Union Version with New Punctuation
    krv(Language.ko, 1961, Books.KOREAN_NUMBER_NAME_MAP, "개역한글", 14), // Korean, Korean Revised Version
    jc(Language.ja, 1955, Books.JAPANESE_JC_NUMBER_NAME_MAP, "口語訳", 15), // Japanese, Colloquial Japanese

    // South East Asia
    kttv(Language.vi, 1925, Books.VIETNAMESE_NUMBER_NAME_MAP, "Kinh Thánh Tiếng Việt", 16), // Vietnamese
    ;

    fun shortName() = when(this){
        webus -> "WEB"
        cunp, krv, jc -> nativeName
        else -> name.uppercase()
    }
}

@Composable
fun getAssetTranslations() = LocalContext.current.assets.list("") !!.filterNot { it.equals("images") || it.equals("webkit") }.map { Translation.valueOf(it) }.sortedBy { it.defaultSortOrder }

enum class ReadingMode { SINGLE, BILINGUAL_SIDE, BILINGUAL_UNDER }

fun main(){
    Translation.entries.forEach { println(it.toString()) }
}