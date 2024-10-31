package com.example.recipe2.data.util

import com.example.recipe2.R
import com.example.recipe2.data.entity.Category
import com.example.recipe2.data.entity.Recipe
import com.example.recipe2.data.model.Ingredient

object SampleData {
    val recipes = listOf(
        Recipe(
            id = 1,
            title = "کیک شکلاتی",
            categoryId = 1,
            imageUrl = R.drawable.img_deser,
            ingredients = listOf(
                Ingredient("آرد", "2 پیمانه"),
                Ingredient("شکر", "1 پیمانه"),
                Ingredient("پودر کاکائو", "1/2 پیمانه"),
                Ingredient("تخم‌مرغ", "3 عدد")
            ),
            directionToCook = "تمام مواد را با هم ترکیب کرده و در فر بپزید."
        ),
        Recipe(
            id = 2,
            title = "کیک موزی",
            categoryId = 1,
            imageUrl = R.drawable.img_benanna_cake,
            ingredients = listOf(
                Ingredient("آرد", "2 پیمانه"),
                Ingredient("شکر", "1 پیمانه"),
                Ingredient("پودر کاکائو", "1/2 پیمانه"),
                Ingredient("تخم‌مرغ", "3 عدد")
            ),
            directionToCook = "تمام مواد را با هم ترکیب کرده و در فر بپزید."
        ),
        Recipe(
            id = 3,
            title = "کیک توت فرنگی",
            categoryId = 1,
            imageUrl = R.drawable.img_ruzberry_cake,
            ingredients = listOf(
                Ingredient("آرد", "2 پیمانه"),
                Ingredient("شکر", "1 پیمانه"),
                Ingredient("پودر کاکائو", "1/2 پیمانه"),
                Ingredient("تخم‌مرغ", "3 عدد")
            ),
            directionToCook = "تمام مواد را با هم ترکیب کرده و در فر بپزید."
        ),
        Recipe(
            id = 4,
            title = "قورمه‌سبزی",
            categoryId = 2,
            imageUrl = R.drawable.img_traditional,
            ingredients = listOf(
                Ingredient("سبزی قورمه", "500 گرم"),
                Ingredient("لوبیا قرمز", "1 پیمانه"),
                Ingredient("گوشت خورشتی", "300 گرم"),
                Ingredient("پیاز", "1 عدد")
            ),
            directionToCook = "سبزی را سرخ کنید، سپس مواد را با هم بپزید."
        ),
        Recipe(
            id = 5,
            title = "سالاد شیرازی",
            categoryId = 3,
            imageUrl = R.drawable.img_saled,
            ingredients = listOf(
                Ingredient("خیار", "2 عدد"),
                Ingredient("گوجه‌فرنگی", "2 عدد"),
                Ingredient("پیاز", "1 عدد"),
                Ingredient("آبلیمو", "2 قاشق غذاخوری")
            ),
            directionToCook = "همه مواد را خرد کرده و مخلوط کنید."
        ),
        Recipe(
            id = 6,
            title = "دوغ سنتی",
            categoryId = 4,
            imageUrl = R.drawable.img_yugert,
            ingredients = listOf(
                Ingredient("ماست", "2 پیمانه"),
                Ingredient("آب", "1 پیمانه"),
                Ingredient("نمک", "به مقدار لازم"),
                Ingredient("نعنا خشک", "1 قاشق چای‌خوری")
            ),
            directionToCook = "ماست و آب را با هم ترکیب کرده و نمک و نعنا را اضافه کنید."
        ),
        Recipe(
            id = 7,
            title = "سوپ جو",
            categoryId = 5,
            imageUrl = R.drawable.img_soup,
            ingredients = listOf(
                Ingredient("جو پرک", "1 پیمانه"),
                Ingredient("هویج", "2 عدد"),
                Ingredient("پیاز", "1 عدد"),
                Ingredient("آب مرغ", "4 پیمانه")
            ),
            directionToCook = "همه مواد را در قابلمه‌ای ریخته و بپزید تا سوپ آماده شود."
        )
    )

    val categories = listOf(
        Category(id = 1, title = "دسر", imageUrl = R.drawable.img_deser),
        Category(id = 2, title = "سنتی", imageUrl = R.drawable.img_traditional),
        Category(id = 3, title = "سالاد", imageUrl = R.drawable.img_saled),
        Category(id = 4, title = "نوشیدنی‌", imageUrl = R.drawable.img_drink),
        Category(id = 5, title = "پیش‌غذا", imageUrl = R.drawable.img_appetizer)
    )
}