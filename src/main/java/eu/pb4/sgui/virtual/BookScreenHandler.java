package eu.pb4.sgui.virtual;

import eu.pb4.sgui.api.gui.BookGui;
import eu.pb4.sgui.api.gui.SimpleGui;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.Slot;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BookScreenHandler extends ScreenHandler {
    public final ItemStack book;
    private final BookGui gui;

    public BookScreenHandler(@Nullable ScreenHandlerType<?> type, int syncId, ItemStack book, BookGui gui, PlayerEntity player) {
        super(type, syncId);
        this.book = book;
        this.gui = gui;

        int n;
        int m;

        PlayerInventory playerInventory = player.inventory;
        this.addSlot(new BookSlot(playerInventory, 0, 0, 0, book));

        for (n = 0; n < 3; ++n) {
            for (m = 0; m < 9; ++m) {
                this.addSlot(new Slot(playerInventory, m + n * 9 + 9, 0, 0));
            }
        }

        for (n = 0; n < 9; ++n) {
            this.addSlot(new Slot(playerInventory, n, 0, 0));
        }

    }


    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }

    @Override
    public void setStackInSlot(int slot, ItemStack stack) {
        if (slot == 0) {
            this.getSlot(slot).setStack(stack);
        } else {
            this.getSlot(slot).setStack(ItemStack.EMPTY);
        }
    }

    @Override
    public void sendContentUpdates() {
        super.sendContentUpdates();
    }

        @Override
    public ItemStack transferSlot(PlayerEntity player, int index) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean canInsertIntoSlot(ItemStack stack, Slot slot) {
        return !(slot instanceof VirtualSlot) && super.canInsertIntoSlot(stack, slot);
    }

    @Override
    public void close(PlayerEntity player) {
        super.close(player);
        this.gui.close(true);
    }

    @Override
    protected boolean insertItem(ItemStack stack, int startIndex, int endIndex, boolean fromLast) {
        return false;
    }
}
