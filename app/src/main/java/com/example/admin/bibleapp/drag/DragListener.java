package com.example.admin.bibleapp.drag;

import com.example.admin.bibleapp.drag.DragController.DragBehavior;

public interface DragListener {
    void onDragEnd();

    void onDragStart(DragSource dragSource, Object obj, DragBehavior dragBehavior);
}
