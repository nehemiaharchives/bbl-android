package org.gnit.bible

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import org.gnit.bible.ui.widgets.devanagariSerifFontFamily
import org.gnit.bible.ui.widgets.enSerifFontFamily
import org.gnit.bible.ui.widgets.jaSerifFontFamily
import org.gnit.bible.ui.widgets.koSerifFontFamily
import org.gnit.bible.ui.widgets.scSerifFontFamily
import org.gnit.bible.ui.widgets.thaiSerifFontFamily

@Composable
fun FontTest() {
    Column{
        Text(text = "Hello World", fontFamily = enSerifFontFamily, style = TextStyle(background = Color.White))
        Text(text = "简化字こんにちは世界 안녕하세요 세상 你好世界 丧个乡 臭", fontFamily = scSerifFontFamily, style = TextStyle(background = Color.White))
        Text(text = "日本語こんにちは世界 안녕하세요 세상 你好世界 丧个乡 臭", fontFamily = jaSerifFontFamily, style = TextStyle(background = Color.White))
        Text(text = "韓国語こんにちは世界 안녕하세요 세상 你好世界 丧个乡 臭", fontFamily = koSerifFontFamily, style = TextStyle(background = Color.White))
        Text(text = "आदिमा परमेश्\u200Dवरले आकाशमण्डल र पृथ्वी सृष्\u200Dटि गर्नुभयो । ", fontFamily = devanagariSerifFontFamily, style = TextStyle(background = Color.White))
        Text(text = "ในปฐมกาล พระเจ้าทรงเนรมิตสร้างฟ้าและแผ่นดิน", fontFamily = thaiSerifFontFamily, style = TextStyle(background = Color.White))
    }
}

@Preview
@Composable
fun FontTestPreview() {
    FontTest()
}