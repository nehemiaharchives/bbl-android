package org.gnit.bible

import android.util.SparseArray

enum class Language {
    en, es, pt, de, fr, ru, nl, it, pl, uk, sv, zh, ko, ja;
}

enum class Translation(val language: Language, val year: Int, val books: SparseArray<String>) {
    // English World
    webus(Language.en, 2000, Books.ENGLISH_NUMBER_NAME_MAP), // webus 206 english, World English Bible
    kjv(Language.en, 1611, Books.ENGLISH_NUMBER_NAME_MAP), //King James Version

    // Latin America
    rvr09(Language.es, 1909, SparseArray(0)/* TODO implement */), // rvr09,        Spanish,    Reina Valera
    tb(Language.pt, 1917, SparseArray(0)/* TODO implement */),    // tb,           Portuguese, Tradução Brasileira

    // Europe
    delut(Language.de, 1912, SparseArray(0)/* TODO implement */), // delut,        German,     Lutherbibel

    // UK already included in English above
    lsg(Language.fr, 1910, SparseArray(0)/* TODO implement */),  // lsg,          French,     Louis Segond Bible
    sinod(Language.ru, 1876, SparseArray(0)/* TODO implement */), // СИНОД(sinod), Russian,    Синодальный перевод
    svrj(Language.nl, 1888, SparseArray(0)/* TODO implement */), // SV-RJ(svrj),  Dutch,      Statenvertaling Jongbloed-editie
    rdv24(Language.it, 1924, SparseArray(0)/* TODO implement */), // rdv24,        Italian,    Revised Diodati Version

    // Spain  already included in Spanish above
    ubg(Language.pl, 2017, SparseArray(0)/* TODO implement */), // ubg,          Polish,     Uwspółcześniona Biblia gdańska
    ubio(Language.uk, 1962, SparseArray(0)/* TODO implement */), // ubio,         Ukrainian,  Біблія в пер. Івана Огієнка
    sven(Language.sv, 1917, SparseArray(0)/* TODO implement */), // sven,         swedish,    1917 års kyrkobibel

    // North East Asia
    cunp(Language.zh, 1919, Books.CHINESE_NUMBER_NAME_MAP),// cunp, Chinese, Chinese Union Version with New Punctuation
    krv(Language.ko, 1961, Books.KOREAN_NUMBER_NAME_MAP),// krv, Korean, Korean Revised Version, 개역한글
    jc(Language.ja, 1955, Books.JAPANESE_JC_NUMBER_NAME_MAP);// jc, Japanese, Japanese, Colloquial Japanese, 口語訳

    fun isAssets() = (this == webus || this == jc)
}

enum class ReadingMode { SINGLE, BILINGUAL_SIDE, BILINGUAL_UNDER }

fun main(){
    Translation.entries.forEach { println(it.toString()) }
}