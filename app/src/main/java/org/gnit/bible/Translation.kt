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
    en, es, pt, de, fr, ru, nl, it, pl, uk, sv, zh, ko, ja;

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
    }

    fun serifFontFamily() = when(this){
        en, es, pt, de, fr, ru, nl, it, pl, uk, sv -> enSerifFontFamily
        zh -> scSerifFontFamily
        ko -> koSerifFontFamily
        ja -> jaSerifFontFamily
    }

    fun sansFontFamily() = when(this){
        en, es, pt, de, fr, ru, nl, it, pl, uk, sv -> enSansFontFamily
        zh -> scSansFontFamily
        ko -> koSansFontFamily
        ja -> jaSansFontFamily
    }
}

@Serializable(with = TranslationSerializer::class)
enum class Translation(val language: Language, val year: Int, val books: SparseArray<String>, val nativeName: String, val defaultSortOrder: Int) {
    // English World
    webus(Language.en, 2000, Books.ENGLISH_NUMBER_NAME_MAP, "World English Bible", 1), // webus 206 english
    kjv(Language.en, 1611, Books.ENGLISH_NUMBER_NAME_MAP, "King James Version", 2),

    // Latin America
    rvr09(Language.es, 1909, SparseArray(0)/* TODO implement */, "Reina Valera", 3), // rvr09,        Spanish
    tb(Language.pt, 1917, SparseArray(0)/* TODO implement */, "Tradução Brasileira", 4),    // tb,           Portuguese

    // Europe
    delut(Language.de, 1912, SparseArray(0)/* TODO implement */, "Lutherbibel", 5), // delut,        German

    // UK already included in English above
    lsg(Language.fr, 1910, SparseArray(0)/* TODO implement */, "Louis Segond Bible", 6),  // lsg,          French
    sinod(Language.ru, 1876, SparseArray(0)/* TODO implement */, "Синодальный перевод", 7), // СИНОД(sinod), Russian
    svrj(Language.nl, 1888, SparseArray(0)/* TODO implement */, "Statenvertaling Jongbloed-editie", 8), // SV-RJ(svrj),  Dutch
    rdv24(Language.it, 1924, SparseArray(0)/* TODO implement */, "Revised Diodati Version", 9), // rdv24,        Italian,

    // Spain  already included in Spanish above
    ubg(Language.pl, 2017, SparseArray(0)/* TODO implement */, "Uwspółcześniona Biblia gdańska", 10), // ubg,          Polish
    ubio(Language.uk, 1962, SparseArray(0)/* TODO implement */, "Біблія в пер. Івана Огієнка", 11), // ubio,         Ukrainian
    sven(Language.sv, 1917, SparseArray(0)/* TODO implement */, "1917 års kyrkobibel", 12), // sven,         swedish

    // North East Asia
    cunp(Language.zh, 1919, Books.CHINESE_NUMBER_NAME_MAP, "和合本", 13),// cunp, Chinese, Chinese Union Version with New Punctuation
    krv(Language.ko, 1961, Books.KOREAN_NUMBER_NAME_MAP, "개역한글", 14),// krv, Korean, Korean Revised Version
    jc(Language.ja, 1955, Books.JAPANESE_JC_NUMBER_NAME_MAP, "口語訳", 15);// jc, Japanese, Japanese, Colloquial Japanese

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