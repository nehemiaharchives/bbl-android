package org.gnit.bible

import android.util.SparseArray

enum class Language {
    en, es, pt, de, fr, ru, nl, it, pl, uk, sv, zh, ko, ja;
}

enum class Translation(val language: Language, val year: Int, val books: SparseArray<String>, val nativeName: String) {
    // English World
    webus(Language.en, 2000, Books.ENGLISH_NUMBER_NAME_MAP, "World English Bible"), // webus 206 english
    kjv(Language.en, 1611, Books.ENGLISH_NUMBER_NAME_MAP, "King James Version"),

    // Latin America
    rvr09(Language.es, 1909, SparseArray(0)/* TODO implement */, "Reina Valera"), // rvr09,        Spanish
    tb(Language.pt, 1917, SparseArray(0)/* TODO implement */, "Tradução Brasileira"),    // tb,           Portuguese

    // Europe
    delut(Language.de, 1912, SparseArray(0)/* TODO implement */, "Lutherbibel"), // delut,        German

    // UK already included in English above
    lsg(Language.fr, 1910, SparseArray(0)/* TODO implement */, "Louis Segond Bible"),  // lsg,          French
    sinod(Language.ru, 1876, SparseArray(0)/* TODO implement */, "Синодальный перевод"), // СИНОД(sinod), Russian
    svrj(Language.nl, 1888, SparseArray(0)/* TODO implement */, "Statenvertaling Jongbloed-editie"), // SV-RJ(svrj),  Dutch
    rdv24(Language.it, 1924, SparseArray(0)/* TODO implement */, "Revised Diodati Version"), // rdv24,        Italian,

    // Spain  already included in Spanish above
    ubg(Language.pl, 2017, SparseArray(0)/* TODO implement */, "Uwspółcześniona Biblia gdańska"), // ubg,          Polish
    ubio(Language.uk, 1962, SparseArray(0)/* TODO implement */, "Біблія в пер. Івана Огієнка"), // ubio,         Ukrainian
    sven(Language.sv, 1917, SparseArray(0)/* TODO implement */, "1917 års kyrkobibel"), // sven,         swedish

    // North East Asia
    cunp(Language.zh, 1919, Books.CHINESE_NUMBER_NAME_MAP, "和合本"),// cunp, Chinese, Chinese Union Version with New Punctuation
    krv(Language.ko, 1961, Books.KOREAN_NUMBER_NAME_MAP, "개역한글"),// krv, Korean, Korean Revised Version
    jc(Language.ja, 1955, Books.JAPANESE_JC_NUMBER_NAME_MAP, "口語訳");// jc, Japanese, Japanese, Colloquial Japanese

    fun isAssets() = (this == webus || this == jc)
}

enum class ReadingMode { SINGLE, BILINGUAL_SIDE, BILINGUAL_UNDER }

fun main(){
    Translation.entries.forEach { println(it.toString()) }
}