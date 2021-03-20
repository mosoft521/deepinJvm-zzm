package org.fenixsoft.jvm.chapter11;

/**
 * VM Args1：-XX:+PrintCompilation
 * VM Args2：-XX:+PrintCompilation -XX:+PrintInlining
 * Error: VM option 'PrintInlining' is diagnostic and must be enabled via -XX:+UnlockDiagnosticVMOptions.
 * Error: Could not create the Java Virtual Machine.
 * Error: A fatal exception has occurred. Program will exit.
 * VM Args3：-XX:+PrintCompilation -XX:+UnlockDiagnosticVMOptions -XX:+PrintInlining
 * VM Args4：-XX:+PrintAssembly
 * Error: VM option 'PrintAssembly' is diagnostic and must be enabled via -XX:+UnlockDiagnosticVMOptions.
 * Error: Could not create the Java Virtual Machine.
 * Error: A fatal exception has occurred. Program will exit
 * VM Args5：-XX:+UnlockDiagnosticVMOptions -XX:+PrintAssembly
 * Java HotSpot(TM) 64-Bit Server VM warning: PrintAssembly is enabled; turning on DebugNonSafepoints to gain additional output
 * VM Args6：-XX:+PrintOptoAssembly
 * Error: VM option 'PrintOptoAssembly' is notproduct and is available only in debug version of VM.
 * Error: Could not create the Java Virtual Machine.
 * Error: A fatal exception has occurred. Program will exit.
 */
public class Test {
    public static final int NUM = 15000;

    public static int doubleValue(int i) {
        //这个空循环用于后面演示JIT代码优化过程
        for (int j = 0; j < 100000; j++) ;
        return i * 2;
    }

    public static long calcSum() {
        long sum = 0;
        for (int i = 1; i <= 100; i++) {
            sum += doubleValue(i);
        }
        return sum;
    }

    public static void main(String[] args) {
        for (int i = 0; i < NUM; i++) {
            calcSum();
        }
    }
}
/*
VM Args1：-XX:+PrintCompilation
被即时编译的代码
其中带有“%”的输出说明是由回边计数器触发的栈，上替换编译

    268    1       3       java.lang.Object::<init> (1 bytes)
    269    2       1       sun.instrument.TransformerManager::getSnapshotTransformerList (5 bytes)
    270    3       3       java.lang.StringBuilder::<init> (7 bytes)
    270    4       3       java.lang.String::equals (81 bytes)
    271    5       3       java.lang.String::<init> (82 bytes)
    271    6       4       java.lang.String::charAt (29 bytes)
    272    7       3       java.lang.String::getChars (62 bytes)
    272    8     n 0       java.lang.System::arraycopy (native)   (static)
    272   10       4       java.lang.String::length (6 bytes)
    272    9       3       java.io.WinNTFileSystem::isLetter (30 bytes)
    272   11       3       java.lang.String::hashCode (55 bytes)
    273   12       3       java.io.WinNTFileSystem::normalize (143 bytes)
    273   14       3       java.lang.String::indexOf (70 bytes)
    273   13       3       java.lang.AbstractStringBuilder::ensureCapacityInternal (27 bytes)
    274   15       3       java.util.Arrays::copyOfRange (63 bytes)
    275   16       3       java.lang.Math::min (11 bytes)
    275   17       3       java.lang.String::indexOf (7 bytes)
    275   18       3       java.lang.Character::toLowerCase (9 bytes)
    275   19       3       java.lang.CharacterDataLatin1::toLowerCase (39 bytes)
    278   20       3       java.lang.String::startsWith (7 bytes)
    278   21       3       java.lang.String::toCharArray (25 bytes)
    278   22       3       java.lang.String::startsWith (72 bytes)
    279   23       3       java.util.HashMap::getNode (148 bytes)
    279   26       3       java.lang.String::indexOf (166 bytes)
    280   24  s    3       java.lang.StringBuffer::append (13 bytes)
    280   25       3       java.lang.AbstractStringBuilder::append (29 bytes)
    280   27       3       java.io.WinNTFileSystem::isSlash (18 bytes)
    280   28       3       java.util.HashMap::hash (20 bytes)
    286   29 %     3       org.fenixsoft.jvm.chapter11.Test::doubleValue @ 2 (18 bytes)
    286   30       3       org.fenixsoft.jvm.chapter11.Test::doubleValue (18 bytes)
    286   31 %     4       org.fenixsoft.jvm.chapter11.Test::doubleValue @ 2 (18 bytes)
    287   29 %     3       org.fenixsoft.jvm.chapter11.Test::doubleValue @ -2 (18 bytes)   made not entrant
    292   32       4       org.fenixsoft.jvm.chapter11.Test::doubleValue (18 bytes)
    293   30       3       org.fenixsoft.jvm.chapter11.Test::doubleValue (18 bytes)   made not entrant
    296   33       3       org.fenixsoft.jvm.chapter11.Test::calcSum (26 bytes)
    297   34 %     4       org.fenixsoft.jvm.chapter11.Test::calcSum @ 4 (26 bytes)
    298   35       4       org.fenixsoft.jvm.chapter11.Test::calcSum (26 bytes)
    300   33       3       org.fenixsoft.jvm.chapter11.Test::calcSum (26 bytes)   made not entrant
VM Args2：-XX:+PrintCompilation -XX:+PrintInlining
要求虚拟机输出方法内联信息
VM Args3：-XX:+PrintCompilation -XX:+UnlockDiagnosticVMOptions -XX:+PrintInlining
    164    1       3       java.lang.String::hashCode (55 bytes)
    165    2       3       java.util.Arrays::copyOf (19 bytes)
                              @ 11   java.lang.Math::min (11 bytes)
                              @ 14   java.lang.System::arraycopy (0 bytes)   intrinsic
    165    3       3       java.lang.StringBuilder::toString (17 bytes)
                              @ 13   java.lang.String::<init> (82 bytes)   callee is too large
    165    4       3       java.lang.CharacterData::of (120 bytes)
    166    5       3       java.lang.CharacterDataLatin1::getProperties (11 bytes)
    166    6       3       java.lang.String::equals (81 bytes)
    166    7       3       java.lang.StringBuilder::<init> (7 bytes)
                              @ 3   java.lang.AbstractStringBuilder::<init> (12 bytes)
                                @ 1   java.lang.Object::<init> (1 bytes)
    166    8       4       java.lang.String::charAt (29 bytes)
    167    9       3       java.lang.Object::<init> (1 bytes)
    167   10       1       sun.instrument.TransformerManager::getSnapshotTransformerList (5 bytes)
    167   11       3       java.lang.String::<init> (82 bytes)
                              @ 1   java.lang.Object::<init> (1 bytes)
                              @ 13  java/lang/StringIndexOutOfBoundsException::<init> (not loaded)   not inlineable
                              @ 30  java/lang/StringIndexOutOfBoundsException::<init> (not loaded)   not inlineable
                              @ 65  java/lang/StringIndexOutOfBoundsException::<init> (not loaded)   not inlineable
                              @ 75   java.util.Arrays::copyOfRange (63 bytes)   callee is too large
    168   12       3       java.lang.String::getChars (62 bytes)
                              @ 9  java/lang/StringIndexOutOfBoundsException::<init> (not loaded)   not inlineable
                              @ 27  java/lang/StringIndexOutOfBoundsException::<init> (not loaded)   not inlineable
                              @ 43  java/lang/StringIndexOutOfBoundsException::<init> (not loaded)   not inlineable
                              @ 58   java.lang.System::arraycopy (0 bytes)   intrinsic
    168   13       3       java.io.WinNTFileSystem::isLetter (30 bytes)
    168   14       4       java.lang.String::length (6 bytes)
    168   15       3       java.io.WinNTFileSystem::normalize (143 bytes)
                              @ 1   java.lang.String::length (6 bytes)
                              @ 31   java.lang.String::charAt (29 bytes)
                                @ 18  java/lang/StringIndexOutOfBoundsException::<init> (not loaded)   not inlineable
                              @ 61   java.io.WinNTFileSystem::normalize (231 bytes)   callee is too large
                              @ 90   java.io.WinNTFileSystem::normalize (231 bytes)   callee is too large
                              @ 111   java.io.WinNTFileSystem::normalize (231 bytes)   callee is too large
                              @ 137   java.io.WinNTFileSystem::normalize (231 bytes)   callee is too large
    170   16       3       java.lang.String::indexOf (70 bytes)
                              @ 66   java.lang.String::indexOfSupplementary (71 bytes)   callee is too large
    170   20     n 0       java.lang.System::arraycopy (native)   (static)
    170   18       3       java.lang.String::startsWith (72 bytes)
    170   17       3       java.lang.String::startsWith (7 bytes)
                              @ 3   java.lang.String::startsWith (72 bytes)   callee is too large
    170   22       3       java.lang.AbstractStringBuilder::ensureCapacityInternal (27 bytes)
                              @ 17   java.lang.AbstractStringBuilder::newCapacity (39 bytes)   callee is too large
                              @ 20   java.util.Arrays::copyOf (19 bytes)
                                @ 11   java.lang.Math::min (11 bytes)
                                @ 14   java.lang.System::arraycopy (0 bytes)   intrinsic
    171   21       3       java.lang.Math::min (11 bytes)
    171   19       3       java.lang.String::toCharArray (25 bytes)
                              @ 20   java.lang.System::arraycopy (0 bytes)   intrinsic
    171   24       3       java.lang.AbstractStringBuilder::append (29 bytes)
                              @ 7   java.lang.AbstractStringBuilder::ensureCapacityInternal (27 bytes)
                                @ 17   java.lang.AbstractStringBuilder::newCapacity (39 bytes)   callee is too large
                                @ 20   java.util.Arrays::copyOf (19 bytes)
                                  @ 11   java.lang.Math::min (11 bytes)
                                  @ 14   java.lang.System::arraycopy (0 bytes)   intrinsic
    172   25       3       java.lang.String::indexOf (166 bytes)
    172   23  s    3       java.lang.StringBuffer::append (13 bytes)
                              @ 7   java.lang.AbstractStringBuilder::append (29 bytes)
                                @ 7   java.lang.AbstractStringBuilder::ensureCapacityInternal (27 bytes)
                                  @ 17   java.lang.AbstractStringBuilder::newCapacity (39 bytes)   callee is too large
                                  @ 20   java.util.Arrays::copyOf (19 bytes)
                                    @ 11   java.lang.Math::min (11 bytes)
                                    @ 14   java.lang.System::arraycopy (0 bytes)   intrinsic
    173   26       3       java.io.WinNTFileSystem::isSlash (18 bytes)
    173   27       3       java.util.Arrays::copyOfRange (63 bytes)
                              @ 16   java.lang.StringBuilder::<init> (7 bytes)
                                @ 3   java.lang.AbstractStringBuilder::<init> (12 bytes)
                                  @ 1   java.lang.Object::<init> (1 bytes)
                              @ 20   java.lang.StringBuilder::append (8 bytes)
                                @ 2   java.lang.AbstractStringBuilder::append (62 bytes)   callee is too large
                              @ 25   java.lang.StringBuilder::append (8 bytes)
                                @ 2   java.lang.AbstractStringBuilder::append (50 bytes)   callee is too large
                              @ 29   java.lang.StringBuilder::append (8 bytes)
                                @ 2   java.lang.AbstractStringBuilder::append (62 bytes)   callee is too large
                              @ 32   java.lang.StringBuilder::toString (17 bytes)
                                @ 13   java.lang.String::<init> (82 bytes)   callee is too large
                              @ 35   java.lang.IllegalArgumentException::<init> (6 bytes)   don't inline Throwable constructors
                              @ 54   java.lang.Math::min (11 bytes)
                              @ 57   java.lang.System::arraycopy (0 bytes)   intrinsic
    174   28       3       java.util.HashMap::hash (20 bytes)
                              @ 9   java.lang.Object::hashCode (0 bytes)   no static binding
    175   29 %     3       org.fenixsoft.jvm.chapter11.Test::doubleValue @ 2 (18 bytes)
    175   30       3       org.fenixsoft.jvm.chapter11.Test::doubleValue (18 bytes)
    175   31 %     4       org.fenixsoft.jvm.chapter11.Test::doubleValue @ 2 (18 bytes)
    176   29 %     3       org.fenixsoft.jvm.chapter11.Test::doubleValue @ -2 (18 bytes)   made not entrant
    176   32       4       org.fenixsoft.jvm.chapter11.Test::doubleValue (18 bytes)
    176   30       3       org.fenixsoft.jvm.chapter11.Test::doubleValue (18 bytes)   made not entrant
    177   33       3       java.lang.String::indexOf (7 bytes)
                              @ 3   java.lang.String::indexOf (70 bytes)   callee is too large
    177   34       3       org.fenixsoft.jvm.chapter11.Test::calcSum (26 bytes)
                              @ 12   org.fenixsoft.jvm.chapter11.Test::doubleValue (18 bytes)   inlining prohibited by policy
    177   35       3       java.lang.Character::toLowerCase (9 bytes)
                              @ 1   java.lang.CharacterData::of (120 bytes)   callee is too large
                              @ 5   java.lang.CharacterData::toLowerCase (0 bytes)   no static binding
    177   36       3       java.lang.CharacterDataLatin1::toLowerCase (39 bytes)
                              @ 4   java.lang.CharacterDataLatin1::getProperties (11 bytes)
    178   37 %     4       org.fenixsoft.jvm.chapter11.Test::calcSum @ 4 (26 bytes)
                              @ 12   org.fenixsoft.jvm.chapter11.Test::doubleValue (18 bytes)   inline (hot)
    179   38       4       org.fenixsoft.jvm.chapter11.Test::calcSum (26 bytes)
                              @ 12   org.fenixsoft.jvm.chapter11.Test::doubleValue (18 bytes)   inline (hot)
    180   34       3       org.fenixsoft.jvm.chapter11.Test::calcSum (26 bytes)   made not entrant
 */