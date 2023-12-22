package org.gnit.bible

import android.util.SparseArray

/**
 * Created by Joel on 10/22/2014.
 */
object Books {
    fun maxChapter(book: Int): Int = when (book) {
        19 -> 150
        23 -> 66
        24 -> 52
        1 -> 50
        26 -> 48
        18 -> 42
        2 -> 40
        4, 14 -> 36
        5 -> 34
        9, 20 -> 31
        13 -> 29
        40, 44 -> 28
        3 -> 27
        12 -> 25
        6, 10, 42 -> 24
        11, 66 -> 22
        7, 43 -> 21
        41, 45, 46 -> 16
        28, 38 -> 14
        16, 47, 58 -> 13
        21, 27 -> 12
        15, 17 -> 10
        30 -> 9
        22 -> 8
        33 -> 7
        48, 49, 54 -> 6
        25, 52, 59, 60, 62 -> 5
        8, 32, 39, 50, 51, 55 -> 4
        29, 34, 35, 36, 53, 56, 61 -> 3
        37 -> 2
        31, 57, 63, 64, 65 -> 1
        else -> 50
    }

    val ENGLISH_NUMBER_NAME_MAP = englishBooks
    private val englishBooks: SparseArray<String>
        get() {

            val map = SparseArray<String>(67)

            map.put(1, "Genesis")
            map.put(2, "Exodus")
            map.put(3, "Leviticus")
            map.put(4, "Numbers")
            map.put(5, "Deuteronomy")
            map.put(6, "Joshua")
            map.put(7, "Judges")
            map.put(8, "Ruth")
            map.put(9, "1 Samuel")
            map.put(10, "2 Samuel")
            map.put(11, "1 Kings")
            map.put(12, "2 Kings")
            map.put(13, "1 Chronicles")
            map.put(14, "2 Chronicles")
            map.put(15, "Ezra")
            map.put(16, "Nehemiah")
            map.put(17, "Esther")
            map.put(18, "Job")
            map.put(19, "Psalms")
            map.put(20, "Proverbs")
            map.put(21, "Ecclesiastes")
            map.put(22, "Song of Solomon")
            map.put(23, "Isaiah")
            map.put(24, "Jeremiah")
            map.put(25, "Lamentations")
            map.put(26, "Ezekiel")
            map.put(27, "Daniel")
            map.put(28, "Hosea")
            map.put(29, "Joel")
            map.put(30, "Amos")
            map.put(31, "Obadiah")
            map.put(32, "Jonah")
            map.put(33, "Micah")
            map.put(34, "Nahum")
            map.put(35, "Habakkuk")
            map.put(36, "Zephaniah")
            map.put(37, "Haggai")
            map.put(38, "Zechariah")
            map.put(39, "Malachi")
            map.put(40, "Matthew")
            map.put(41, "Mark")
            map.put(42, "Luke")
            map.put(43, "John")
            map.put(44, "Acts")
            map.put(45, "Romans")
            map.put(46, "1 Corinthians")
            map.put(47, "2 Corinthians")
            map.put(48, "Galatians")
            map.put(49, "Ephesians")
            map.put(50, "Philippians")
            map.put(51, "Colossians")
            map.put(52, "1 Thessalonians")
            map.put(53, "2 Thessalonians")
            map.put(54, "1 Timothy")
            map.put(55, "2 Timothy")
            map.put(56, "Titus")
            map.put(57, "Philemon")
            map.put(58, "Hebrews")
            map.put(59, "James")
            map.put(60, "1 Peter")
            map.put(61, "2 Peter")
            map.put(62, "1 John")
            map.put(63, "2 John")
            map.put(64, "3 John")
            map.put(65, "Jude")
            map.put(66, "Revelation")
            return map
        }

    val CHINESE_NUMBER_NAME_MAP = chineseBooks
    private val chineseBooks: SparseArray<String>
        get() {
            val map = SparseArray<String>(67)

            map.put(1, "创世记")
            map.put(2, "出埃及")
            map.put(3, "利未记")
            map.put(4, "民数记")
            map.put(5, "申命记")
            map.put(6, "约书亚记")
            map.put(7, "士师记")
            map.put(8, "路得记")
            map.put(9, "撒母耳记上")
            map.put(10, "撒母耳记下")
            map.put(11, "列王纪上")
            map.put(12, "列王纪下")
            map.put(13, "历代志上")
            map.put(14, "历代志下")
            map.put(15, "以斯拉记")
            map.put(16, "尼希米记")
            map.put(17, "以斯帖记")
            map.put(18, "约伯记")
            map.put(19, "诗篇")
            map.put(20, "箴言")
            map.put(21, "传道书")
            map.put(22, "雅歌")
            map.put(23, "以赛亚书")
            map.put(24, "耶利米书")
            map.put(25, "耶利米哀歌")
            map.put(26, "以西结书")
            map.put(27, "但以理书")
            map.put(28, "何西阿书")
            map.put(29, "约珥书")
            map.put(30, "阿摩司书")
            map.put(31, "俄巴底亚书")
            map.put(32, "约拿书")
            map.put(33, "弥迦书")
            map.put(34, "那鸿书")
            map.put(35, "哈巴谷书")
            map.put(36, "西番雅书")
            map.put(37, "哈该书")
            map.put(38, "撒迦利亚书")
            map.put(39, "玛拉基书")
            map.put(40, "马太福音")
            map.put(41, "马可福音")
            map.put(42, "路加福音")
            map.put(43, "约翰福音")
            map.put(44, "使徒行传")
            map.put(45, "罗马书")
            map.put(46, "哥林多前书")
            map.put(47, "哥林多后书")
            map.put(48, "加拉太书")
            map.put(49, "以弗所书")
            map.put(50, "腓立比书")
            map.put(51, "歌罗西书")
            map.put(52, "帖撒罗尼迦前书")
            map.put(53, "帖撒罗尼迦后书")
            map.put(54, "提摩太前书")
            map.put(55, "提摩太后书")
            map.put(56, "提多书")
            map.put(57, "腓利门书")
            map.put(58, "希伯来书")
            map.put(59, "雅各书")
            map.put(60, "彼得前书")
            map.put(61, "彼得后书")
            map.put(62, "约翰一书")
            map.put(63, "约翰二书")
            map.put(64, "约翰三书")
            map.put(65, "犹大书")
            map.put(66, "启示录")

            return map
        }

    val KOREAN_NUMBER_NAME_MAP = koreanBooks
    private val koreanBooks: SparseArray<String>
        get() {
            val map = SparseArray<String>(67)

            map.put(1, "창세기")
            map.put(2, "출애굽기")
            map.put(3, "레위기")
            map.put(4, "민수기")
            map.put(5, "신명기")
            map.put(6, "여호수아")
            map.put(7, "사사기")
            map.put(8, "룻기")
            map.put(9, "사무엘상")
            map.put(10, "사무엘하")
            map.put(11, "열왕기상")
            map.put(12, "열왕기하")
            map.put(13, "역대상")
            map.put(14, "역대하")
            map.put(15, "에스라")
            map.put(16, "느헤미야")
            map.put(17, "에스더")
            map.put(18, "욥기")
            map.put(19, "시편")
            map.put(20, "잠언")
            map.put(21, "전도서")
            map.put(22, "아가")
            map.put(23, "이사야")
            map.put(24, "예레미야")
            map.put(25, "예레미야애가")
            map.put(26, "에스겔")
            map.put(27, "다니엘")
            map.put(28, "호세아")
            map.put(29, "요엘")
            map.put(30, "아모스")
            map.put(31, "오바댜")
            map.put(32, "요나")
            map.put(33, "미가")
            map.put(34, "나훔")
            map.put(35, "하박국")
            map.put(36, "스바냐")
            map.put(37, "학개")
            map.put(38, "스가랴")
            map.put(39, "말라기")
            map.put(40, "마태복음")
            map.put(41, "마가복음")
            map.put(42, "누가복음")
            map.put(43, "요한복음")
            map.put(44, "사도행전")
            map.put(45, "로마서")
            map.put(46, "고린도전서")
            map.put(47, "고린도후서")
            map.put(48, "갈라디아서")
            map.put(49, "에베소서")
            map.put(50, "빌립보서")
            map.put(51, "골로새서")
            map.put(52, "데살로니가전서")
            map.put(53, "데살로니가후서")
            map.put(54, "디모데전서")
            map.put(55, "디모데후서")
            map.put(56, "디도서")
            map.put(57, "빌레몬서")
            map.put(58, "히브리서")
            map.put(59, "야고보서")
            map.put(60, "베드로전서")
            map.put(61, "베드로후서")
            map.put(62, "요한1서")
            map.put(63, "요한2서")
            map.put(64, "요한3서")
            map.put(65, "유다서")
            map.put(66, "요한계시록")

            return map
        }

    val JAPANESE_JC_NUMBER_NAME_MAP = japaneseJcBooks
    private val japaneseJcBooks: SparseArray<String>
        get(){
            val map = SparseArray<String>(67)

            map.put(1, "創世記")
            map.put(2, "出エジプト記")
            map.put(3, "レビ記")
            map.put(4, "民数記")
            map.put(5, "申命記")
            map.put(6, "ヨシュア記")
            map.put(7, "士師記")
            map.put(8, "ルツ記")
            map.put(9, "サムエル記上")
            map.put(10, "サムエル記下")
            map.put(11, "列王記上")
            map.put(12, "列王記下")
            map.put(13, "歴代誌上")
            map.put(14, "歴代誌下")
            map.put(15, "エズラ記")
            map.put(16, "ネヘミヤ記")
            map.put(17, "エステル記")
            map.put(18, "ヨブ記")
            map.put(19, "詩篇")
            map.put(20, "箴言")
            map.put(21, "伝道の書")
            map.put(22, "雅歌")
            map.put(23, "イザヤ書")
            map.put(24, "エレミヤ書")
            map.put(25, "哀歌")
            map.put(26, "エゼキエル書")
            map.put(27, "ダニエル書")
            map.put(28, "ホセア書")
            map.put(29, "ヨエル書")
            map.put(30, "アモス書")
            map.put(31, "オバデヤ書")
            map.put(32, "ヨナ書")
            map.put(33, "ミカ書")
            map.put(34, "ナホム書")
            map.put(35, "ハバクク書")
            map.put(36, "ゼパニヤ書")
            map.put(37, "ハガイ書")
            map.put(38, "ゼカリヤ書")
            map.put(39, "マラキ書")
            map.put(40, "マタイによる福音書")
            map.put(41, "マルコによる福音書")
            map.put(42, "ルカによる福音書")
            map.put(43, "ヨハネによる福音書")
            map.put(44, "使徒行伝")
            map.put(45, "ローマ人への手紙")
            map.put(46, "コリント人への第一の手紙")
            map.put(47, "コリント人への第二の手紙")
            map.put(48, "ガラテヤ人への手紙")
            map.put(49, "エペソ人への手紙")
            map.put(50, "ピリピ人への手紙")
            map.put(51, "コロサイ人への手紙")
            map.put(52, "テサロニケ人への第一の手紙")
            map.put(53, "テサロニケ人への第二の手紙")
            map.put(54, "テモテへの第一の手紙")
            map.put(55, "テモテへの第二の手紙")
            map.put(56, "テトスへの手紙")
            map.put(57, "ピレモンへの手紙")
            map.put(58, "ヘブル人への手紙")
            map.put(59, "ヤコブの手紙")
            map.put(60, "ペテロの第一の手紙")
            map.put(61, "ペテロの第二の手紙")
            map.put(62, "ヨハネの第一の手紙")
            map.put(63, "ヨハネの第二の手紙")
            map.put(64, "ヨハネの第三の手紙")
            map.put(65, "ユダの手紙")
            map.put(66, "ヨハネの黙示録")
            return map
        }
}
