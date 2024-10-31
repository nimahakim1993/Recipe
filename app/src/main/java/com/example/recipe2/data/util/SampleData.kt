package com.example.recipe2.data.util

import com.example.recipe2.data.entity.Category
import com.example.recipe2.data.entity.Recipe
import com.example.recipe2.data.model.Ingredient

object SampleData {
    val recipes = listOf(
        Recipe(
            id = 1,
            title = "کیک شکلاتی",
            categoryId = 1,
            imageUrl = "https://example.com/chocolate_cake.jpg",
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
            title = "قورمه‌سبزی",
            categoryId = 1,
            imageUrl = "https://example.com/ghormeh_sabzi.jpg",
            ingredients = listOf(
                Ingredient("سبزی قورمه", "500 گرم"),
                Ingredient("لوبیا قرمز", "1 پیمانه"),
                Ingredient("گوشت خورشتی", "300 گرم"),
                Ingredient("پیاز", "1 عدد")
            ),
            directionToCook = "سبزی را سرخ کنید، سپس مواد را با هم بپزید."
        ),
        Recipe(
            id = 3,
            title = "سالاد شیرازی",
            categoryId = 3,
            imageUrl = "https://example.com/salad_shirazi.jpg",
            ingredients = listOf(
                Ingredient("خیار", "2 عدد"),
                Ingredient("گوجه‌فرنگی", "2 عدد"),
                Ingredient("پیاز", "1 عدد"),
                Ingredient("آبلیمو", "2 قاشق غذاخوری")
            ),
            directionToCook = "همه مواد را خرد کرده و مخلوط کنید."
        ),
        Recipe(
            id = 4,
            title = "دوغ سنتی",
            categoryId = 4,
            imageUrl = "https://example.com/doogh.jpg",
            ingredients = listOf(
                Ingredient("ماست", "2 پیمانه"),
                Ingredient("آب", "1 پیمانه"),
                Ingredient("نمک", "به مقدار لازم"),
                Ingredient("نعنا خشک", "1 قاشق چای‌خوری")
            ),
            directionToCook = "ماست و آب را با هم ترکیب کرده و نمک و نعنا را اضافه کنید."
        ),
        Recipe(
            id = 5,
            title = "سوپ جو",
            categoryId = 5,
            imageUrl = "https://example.com/barley_soup.jpg",
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
        Category(id = 1, title = "دسر"),
        Category(id = 2, title = "غذاهای"),
        Category(id = 3, title = "سالاد"),
        Category(id = 4, title = "نوشیدنی‌"),
        Category(id = 5, title = "پیش‌غذا")
    )
}