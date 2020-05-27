package sample;

import javafx.scene.control.ListView;
import se.chalmers.cse.dat216.project.ProductCategory;

import java.util.Arrays;

import static se.chalmers.cse.dat216.project.ProductCategory.*;

/*

    -POD,
    -BREAD,
    -BERRY,
    -CITRUS_FRUIT,
    -HOT_DRINKS,
    -COLD_DRINKS,
    -EXOTIC_FRUIT,
    -FISH,
    -VEGETABLE_FRUIT,
    -CABBAGE,
    -MEAT,
    -DAIRIES,
    -MELONS,
    -FLOUR_SUGAR_SALT,
    -NUTS_AND_SEEDS,
    -PASTA,
    -POTATO_RICE,
    -ROOT_VEGETABLE,
    -FRUIT,
    -SWEET,
    -HERB;
 */
public class CategoryManager {
    public static FrontendCategory currentCategory = FrontendCategory.ALLA_KATEGORIER;
    public static ListView<String> categoriesListView;

    public static void goToCategory(FrontendCategory category) {
        int index = Arrays.asList(FrontendCategory.values()).indexOf(category);
        categoriesListView.getSelectionModel().select(index);
    }

    public enum FrontendCategory {
        ALLA_KATEGORIER(ProductCategory.values()),
        FRUKT_OCH_BÄR(new ProductCategory[]{
                BERRY,
                CITRUS_FRUIT,
                EXOTIC_FRUIT,
                MELONS,
                FRUIT,
        }),
        GRÖNSAKER_OCH_VÄXTER(new ProductCategory[]{
                POD,
                HERB,
                VEGETABLE_FRUIT,
                CABBAGE,
                ROOT_VEGETABLE,
        }),
        KÖTT_OCH_FISK(new ProductCategory[]{
                FISH,
                MEAT,
        }),
        TORRVAROR(new ProductCategory[]{
                FLOUR_SUGAR_SALT,
                NUTS_AND_SEEDS,
        }),
        DRYCK_OCH_GODIS(new ProductCategory[]{
                HOT_DRINKS,
                COLD_DRINKS,
                SWEET,
        }),
        BRÖD(new ProductCategory[]{
                BREAD,
        }),
        MEJERI_OCH_ÄGG(new ProductCategory[]{
                DAIRIES,
        }),
        PASTA_RIS_POTATIS(new ProductCategory[]{
                PASTA,
                POTATO_RICE,
        });

        public final ProductCategory[] productCategories;

        FrontendCategory(ProductCategory[] productCategories) {
            this.productCategories = productCategories;
        }
    }
}
