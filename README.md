## 什么是SkyblockLite

SkyBlockLite是一个已经跑路的客户，由苏辰编写，本储存库是SkyBlockLite的反混淆和反编译代码，已经到了可以阅读的程度

例如 AutoClicker

```java
    @EventTarget
    public void onUpdate(TickEvent var1_1) {
        var2_2 = new DecimalFormat("0.00");
        var3_3 = AutoClick.mc.thePlayer.rayTrace(4.0, 0.0f).getBlockPos();
        if (AutoClick.mc.objectMouseOver.typeOfHit != MovingObjectPosition.MovingObjectType.BLOCK) ** GOTO lbl-1000
        if (AutoClick.mc.theWorld.getBlockState(AutoClick.mc.objectMouseOver.getBlockPos()).getBlock() == Blocks.air) ** GOTO lbl-1000
        if (AutoClick.mc.objectMouseOver.typeOfHit != MovingObjectPosition.MovingObjectType.ENTITY) {
            v0 = true;
        } else lbl-1000:
        // 3 sources

        {
            v0 = false;
        }
        var4_4 = v0;
        this.setDisplayName("CPS:" + this.mincps.getValueState().intValue() + "-" + this.maxcps.getValueState().intValue());
        if (!AutoClick.mc.gameSettings.keyBindAttack.isKeyDown()) {
            return;
        }
        if (!this.time.delay(this.delay)) {
            return;
        }
        if (AutoClick.mc.currentScreen != null) {
            return;
        }
        if (this.startDelay < this.StartDelay.getValueState()) {
            return;
        }
        if (var4_4) {
            return;
        }
        AutoClick.mc.clickMouse();
        AutoClick.mc.leftClickCounter = 0;
        this.delay();
        this.time.reset();
    }
```

## 可以运行吗

不可以，因为没修复错误

而且丢失了一部分注解和方法的代码，无法运行

但是不影响抄袭，除非你想用SkyblockLite作为base

## 可以抄袭里面的代码吗

当然可以，这也不是我写的代码.

但是里面的代码非常狗屎，不建议抄袭

## 其他

苏辰没有使用static变量在MotionEvent

```java
public class MotionEvent
implements Event {
    public double x;
    public double y;
    public double z;

    public MotionEvent(double d, double d2, double d3) {
        this.x = d;
        this.y = d2;
        this.z = d3;
    }
}
```
