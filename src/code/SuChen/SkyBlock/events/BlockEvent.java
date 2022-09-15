/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.BlockPos
 *  net.minecraft.util.EnumFacing
 */
package code.SuChen.SkyBlock.events;

import com.darkmagician6.eventapi.events.Event;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

public class BlockEvent
implements Event {
    private BlockPos pos;
    private EnumFacing face;
    private boolean cancelled;

    public BlockEvent(BlockPos blockPos, EnumFacing enumFacing) {
        this.pos = blockPos;
        this.face = enumFacing;
    }
}

