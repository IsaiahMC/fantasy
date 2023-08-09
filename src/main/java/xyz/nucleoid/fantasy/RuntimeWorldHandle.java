package xyz.nucleoid.fantasy;

import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;

public final class RuntimeWorldHandle {
    private final Fantasy fantasy;
    private final ServerWorld world;

    RuntimeWorldHandle(Fantasy fantasy, ServerWorld world) {
        this.fantasy = fantasy;
        this.world = world;
    }

    public void setTickWhenEmpty(boolean tickWhenEmpty) {
        ((FantasyWorldAccess) this.world).fantasy$setTickWhenEmpty(tickWhenEmpty);
    }

    public void delete() {
        this.fantasy.enqueueWorldDeletion(this.world);
    }

    /**
     * Unloads the world. It only deletes the files if world is temporary.
     */
    public void unload() {
        this.fantasy.enqueueWorldUnloading(this.world);
    }

    public ServerWorld asWorld() {
        return this.world;
    }

    public RegistryKey<World> getRegistryKey() {
        return this.world.getRegistryKey();
    }
}
