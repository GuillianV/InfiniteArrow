package net.guillian.tutorialmod.common.utils;

import net.minecraft.core.BlockPos;

import java.util.ArrayList;
import java.util.List;

public class SphereCalculator {

    static BlockPos offset(BlockPos startPos, float x, float y, float z) {
        return startPos.offset((int) x, (int) y, (int) z);
    }

    static int maxValue(int[] values) {
        int max = values[0];
        for (int val : values) {
            if (val > max) {
                max = val;
            }
        }
        return max;
    }

    public static List<BlockPos> CircleFilledPositions(BlockPos startPos, int zNeg, int xPos, int zPos, int xNeg) {
        List<BlockPos> allBlocksPos = new ArrayList<>();
        int radius = maxValue(new int[]{zNeg, xPos, zPos, xNeg});
        float zNegPortion = zNeg / (float) radius;
        float xPosPortion = xPos / (float) radius;
        float zPosPortion = zPos / (float) radius;
        float xNegPortion = xNeg / (float) radius;
        radius++;

        for (int x = -radius; x < radius; x++) {
            for (int z = -radius; z < radius; z++) {
                if (x * x + z * z < radius * radius) {
                    BlockPos pos = startPos;
                    if (x <= 0 && z <= 0) {
                        pos = offset(startPos, x * xNegPortion, 0, z * zNegPortion);
                    } else if (x >= 0 && z <= 0) {
                        pos = offset(startPos, x * xPosPortion, 0, z * zNegPortion);
                    } else if (x <= 0 && z >= 0) {
                        pos = offset(startPos, x * xNegPortion, 0, z * zPosPortion);
                    } else if (x >= 0 && z >= 0) {
                        pos = offset(startPos, x * xPosPortion, 0, z * zPosPortion);
                    }
                    addPos(allBlocksPos, pos);
                }
            }
        }
        return allBlocksPos;
    }

    public static List<BlockPos> CircleOutlinePositions(BlockPos startPos, int zNeg, int xPos, int zPos, int xNeg) {
        List<BlockPos> allBlocksPos = new ArrayList<>();
        int radius = maxValue(new int[]{zNeg, xPos, zPos, xNeg});
        float zNegPortion = zNeg / (float) radius;
        float xPosPortion = xPos / (float) radius;
        float zPosPortion = zPos / (float) radius;
        float xNegPortion = xNeg / (float) radius;

        for (float a = 0.01f; a < Math.PI * 2; a += 0.01) {
            float x = (float) (Math.cos(a) * (radius + 1));
            float z = (float) (Math.sin(a) * (radius + 1));
            BlockPos pos = startPos;
            if (x <= 0 && z <= 0) {
                pos = offset(startPos, x * xNegPortion, 0, z * zNegPortion);
            } else if (x >= 0 && z <= 0) {
                pos = offset(startPos, x * xPosPortion, 0, z * zNegPortion);
            } else if (x <= 0 && z >= 0) {
                pos = offset(startPos, x * xNegPortion, 0, z * zPosPortion);
            } else if (x >= 0 && z >= 0) {
                pos = offset(startPos, x * xPosPortion, 0, z * zPosPortion);
            }
            addPos(allBlocksPos, pos);
        }
        return allBlocksPos;
    }

    public static List<BlockPos> SphereFilledPositions(BlockPos startPos, int zNeg, int xPos, int zPos, int xNeg, int up, int down) {
        List<BlockPos> allBlocksPos = new ArrayList<>();
        int radius = maxValue(new int[]{zNeg, xPos, zPos, xNeg, up, down});
        float zNegPortion = zNeg / (float) radius;
        float xPosPortion = xPos / (float) radius;
        float zPosPortion = zPos / (float) radius;
        float xNegPortion = xNeg / (float) radius;
        float upPortion = up / (float) radius;
        float downPortion = down / (float) radius;
        radius++;

        for (float x = -radius; x < radius; x++) {
            for (float y = -radius; y < radius; y++) {
                for (float z = -radius; z < radius; z++) {
                    if (x * x + y * y + z * z < radius * radius) {
                        BlockPos pos = startPos;
                        if (x <= 0 && y <= 0 && z <= 0) {
                            pos = offset(startPos, x * xNegPortion, y * downPortion, z * zNegPortion);
                        } else if (x >= 0 && y <= 0 && z <= 0) {
                            pos = offset(startPos, x * xPosPortion, y * downPortion, z * zNegPortion);
                        } else if (x <= 0 && y <= 0 && z >= 0) {
                            pos = offset(startPos, x * xNegPortion, y * downPortion, z * zPosPortion);
                        } else if (x >= 0 && y <= 0 && z >= 0) {
                            pos = offset(startPos, x * xPosPortion, y * downPortion, z * zPosPortion);
                        } else if (x <= 0 && y >= 0 && z <= 0) {
                            pos = offset(startPos, x * xNegPortion, y * upPortion, z * zNegPortion);
                        } else if (x >= 0 && y >= 0 && z <= 0) {
                            pos = offset(startPos, x * xPosPortion, y * upPortion, z * zNegPortion);
                        } else if (x <= 0 && y >= 0 && z >= 0) {
                            pos = offset(startPos, x * xNegPortion, y * upPortion, z * zPosPortion);
                        } else if (x >= 0 && y >= 0 && z >= 0) {
                            pos = offset(startPos, x * xPosPortion, y * upPortion, z * zPosPortion);
                        }
                        addPos(allBlocksPos, pos);
                    }
                }
            }
        }
        return allBlocksPos;
    }

    public static List<BlockPos> SphereOutlinePositions(BlockPos startPos, int zNeg, int xPos, int zPos, int xNeg, int up, int down) {
        List<BlockPos> allBlocksPos = new ArrayList<>();
        int radius = maxValue(new int[]{zNeg, xPos, zPos, xNeg, up, down});
        float zNegPortion = zNeg / (float) radius;
        float xPosPortion = xPos / (float) radius;
        float zPosPortion = zPos / (float) radius;
        float xNegPortion = xNeg / (float) radius;
        float upPortion = up / (float) radius;
        float downPortion = down / (float) radius;
        radius++;

        for (double a1 = 0; a1 < Math.PI * 2; a1 += Math.PI / (radius * 4)) {
            for (double a2 = 0; a2 < Math.PI * 2; a2 += Math.PI / (radius * 4)) {
                int x = (int) (Math.sin(a1) * Math.cos(a2) * radius);
                int y = (int) (Math.sin(a1) * Math.sin(a2) * radius);
                int z = (int) (Math.cos(a1) * radius);
                BlockPos pos = startPos;
                if (x <= 0 && y <= 0 && z <= 0) {
                    pos = offset(startPos, x * xNegPortion, y * downPortion, z * zNegPortion);
                } else if (x >= 0 && y <= 0 && z <= 0) {
                    pos = offset(startPos, x * xPosPortion, y * downPortion, z * zNegPortion);
                } else if (x <= 0 && y <= 0 && z >= 0) {
                    pos = offset(startPos, x * xNegPortion, y * downPortion, z * zPosPortion);
                } else if (x >= 0 && y <= 0 && z >= 0) {
                    pos = offset(startPos, x * xPosPortion, y * downPortion, z * zPosPortion);
                } else if (x <= 0 && y >= 0 && z <= 0) {
                    pos = offset(startPos, x * xNegPortion, y * upPortion, z * zNegPortion);
                } else if (x >= 0 && y >= 0 && z <= 0) {
                    pos = offset(startPos, x * xPosPortion, y * upPortion, z * zNegPortion);
                } else if (x <= 0 && y >= 0 && z >= 0) {
                    pos = offset(startPos, x * xNegPortion, y * upPortion, z * zPosPortion);
                } else if (x >= 0 && y >= 0 && z >= 0) {
                    pos = offset(startPos, x * xPosPortion, y * upPortion, z * zPosPortion);
                }
                addPos(allBlocksPos, pos);
            }
        }
        return allBlocksPos;
    }

    public static List<BlockPos> CuboidFilledPositions(BlockPos startPos, int zNeg, int xPos, int zPos, int xNeg, int up, int down) {
        List<BlockPos> allBlocksPos = new ArrayList<>();

        for (int x = -xNeg; x < xPos + 1; x++) {
            for (int y = -down; y < up + 1; y++) {
                for (int z = -zNeg; z < zPos + 1; z++) {
                    addPos(allBlocksPos, offset(startPos, x, y, z));
                }
            }
        }
        return allBlocksPos;
    }

    public static List<BlockPos> CuboidOutlinePositions(BlockPos startPos, int zNeg, int xPos, int zPos, int xNeg, int up, int down) {
        List<BlockPos> allBlocksPos = new ArrayList<>();

        for (int x = -xNeg; x < xPos + 1; x++) {
            for (int z = -zNeg; z < zPos + 1; z++) {
                addPos(allBlocksPos, offset(startPos, x, -down, z));
                addPos(allBlocksPos, offset(startPos, x, up, z));
            }
        }

        for (int x = -xNeg; x < xPos + 1; x++) {
            for (int y = -down; y < up + 1; y++) {
                addPos(allBlocksPos, offset(startPos, x, y, -zNeg));
                addPos(allBlocksPos, offset(startPos, x, y, zPos));
            }
        }

        for (int z = -zNeg; z < zPos + 1; z++) {
            for (int y = -down; y < up + 1; y++) {
                addPos(allBlocksPos, offset(startPos, -xNeg, y, z));
                addPos(allBlocksPos, offset(startPos, xPos, y, z));
            }
        }

        return allBlocksPos;
    }

    public static void addPos(List<BlockPos> currentList, BlockPos addedPos) {
        if (currentList.isEmpty() || !currentList.contains(addedPos)) {
            currentList.add(addedPos);
        }
    }

}
