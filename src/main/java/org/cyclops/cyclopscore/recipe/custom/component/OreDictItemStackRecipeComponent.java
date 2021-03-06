package org.cyclops.cyclopscore.recipe.custom.component;

import lombok.Getter;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import org.cyclops.cyclopscore.datastructure.Wrapper;

import java.util.List;
import java.util.Objects;

/**
 * A {@link org.cyclops.cyclopscore.recipe.custom.api.IRecipe} component (input, output or properties) that holds an
 * oredictionary key.
 *
 * @author immortaleeb
 */
public class OreDictItemStackRecipeComponent extends ItemStackRecipeComponent {

    @Getter private final String key;
    private final Wrapper<List<ItemStack>> itemStacks;

    public OreDictItemStackRecipeComponent(String key) {
        super(null);
        this.key = key;
        this.itemStacks = new Wrapper<>(OreDictionary.getOres(Objects.requireNonNull(key)));
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ItemStackRecipeComponent)) return false;
        ItemStackRecipeComponent that = (ItemStackRecipeComponent)object;

        // To increase performance, first check if the comparing stack is not null before
        // potentially matching it with the whole oredict.
        if (that.getItemStack() != null) {
            for (ItemStack itemStack : getItemStacks()) {
                if (equals(itemStack, that.getItemStack())) {
                    return true;
                }
            }
            return false;
        }

        return getItemStacks().isEmpty();
    }

    @Override
    public ItemStack getItemStack() {
        return getItemStacks().size() > 0 ? getItemStacks().get(0) : super.getItemStack();
    }

    @Override
    public List<ItemStack> getItemStacks() {
        return itemStacks.get();
    }

    @Override
    public int hashCode() {
        return key != null ? key.hashCode() + 876 : 0;
    }

}
